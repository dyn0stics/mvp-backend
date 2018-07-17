package io.dyno.mvp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dyno.mvp.contracts.Dyno;
import io.dyno.mvp.controller.SystemController;
import io.dyno.mvp.model.UserProfile;
import io.dyno.mvp.service.UserService;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    Web3j web3j;
    @Value("${dyno.contract.address}")
    private String CONTRACT_ADDRESS;
    @Value("${dyno.contract.fundsAccount}")
    private String TEST_ACCOUNT_PK;
    @Autowired
    IPFS ipfs;

    @Override
    public UserProfile registerUser(String username) throws Exception {
        final UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        final String seed = UUID.randomUUID().toString();
        final ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        final BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
        final String sPrivatekeyInHex = privateKeyInDec.toString(16);
        final WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
        final String sAddress = aWallet.getAddress();

        userProfile.setPrivateKey(sPrivatekeyInHex);
        userProfile.setAddress("0x" + sAddress);

        final Credentials credentials = Credentials.create(userProfile.getPrivateKey());
        log.info("Address: " + credentials.getAddress());
        // Transfer 1 ETH to new address - Test purposes
        final Credentials fundsAccount = Credentials.create(TEST_ACCOUNT_PK);
        Transfer.sendFunds(web3j, fundsAccount, credentials.getAddress(), BigDecimal.valueOf(1), Convert.Unit.ETHER).send();
        // Test method END

        final NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("data", profile2Json(userProfile).getBytes());
        final MerkleNode addResult = ipfs.add(file).get(0);
        log.info("Saved IPFS hash: " + addResult.hash.toBase58());
        userProfile.setIpfsHash(addResult.hash.toBase58());

        final EthGetBalance ethGetBalance = web3j
                .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        log.info("Balance: " + ethGetBalance.getBalance());
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));
        contract.createUser(stringToBytes32(userProfile.getUsername()).getValue(), stringToBytes32("not-available").getValue()).send();
        log.info("Profile created for address " + credentials.getAddress() + " username " + userProfile.getUsername());
        return userProfile;
    }

    @Override
    public UserProfile loginUser(String privateKey) throws Exception {
        final UserProfile userProfile = new UserProfile();
        return userProfile;
    }

    private static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }

    private String profile2Json(final UserProfile userProfile) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userProfile);
    }
}
