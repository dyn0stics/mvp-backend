package io.dyno.mvp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dyno.mvp.contracts.Dyno;
import io.dyno.mvp.model.UserData;
import io.dyno.mvp.model.UserProfile;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MvpApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MvpApplicationTests.class);

    private String NODE_URL = "http://127.0.0.1:7545";
    final String CONTRACT_ADDRESS = "0xf76fba4d55300e1144cf58df49d6f8e656d42959";
    final String USER_PK = "c28463ee0cb84ffdabb1edabf10d24497d7e3e562657d21ac64f1855fb1a6d6a";

    @Test
    public void deployContract() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final Credentials credentials = Credentials.create(USER_PK);
        final Dyno dyno = Dyno.deploy(web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975")).send();
        log.info("Contract deployed at: " + dyno.getContractAddress());
    }

    @Test
    public void transferFunds() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final Credentials credentials = Credentials.create(USER_PK);
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, "0x8316c49dcdcf7912d7494f1964c60924f7d74114", BigDecimal.valueOf(0.001), Convert.Unit.ETHER).send();
    }

    @Test
    public void getUsernameByAddress() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final Credentials credentials = Credentials.create(USER_PK);
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

        byte[] username = contract.getUsernameByIndex(new BigInteger("2")).send();
        log.info("Username " + new String(username));
        log.info("Number of users: " + contract.getUserCount().send().intValue());
    }

    @Test
    public void createUserContract() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final Credentials credentials = Credentials.create(USER_PK);
        log.info("Address: " + credentials.getAddress());
        EthGetBalance ethGetBalance = web3j
                .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        log.info("Balance: " + ethGetBalance.getBalance());
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

        contract.createUser(stringToBytes32("dyno").getValue(), stringToBytes32("dyno").getValue()).send();
        log.info("Number of users: " + contract.getUserCount().send().intValue());
    }

    @Test
    public void ipfsConnectTest() throws IOException {
        final IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        List<Multihash> multihashes = ipfs.refs.local();
        log.info("IPFS hashes " + Arrays.toString(multihashes.toArray()));
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("data", profile2Json(generateRandomUser()).getBytes());
        MerkleNode addResult = ipfs.add(file).get(0);
        log.info("Saved IPFS hash: " + addResult.hash.toBase58());
        Multihash filePointer = Multihash.fromBase58(addResult.hash.toBase58());
        byte[] fileContents = ipfs.cat(filePointer);
        log.info("Retreived: " + new String(fileContents));
    }

    private static Bytes32 stringToBytes32(final String str) {
        byte[] byteValue = str.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }

    private UserProfile generateRandomUser() {
        final UserProfile profile = new UserProfile();
        profile.setUsername(UUID.randomUUID().toString());
        final UserData data = new UserData();
        data.setAge("21");
        data.setCountry("Germany");
        data.setGender("male");
        data.setWeight("80");
        data.setWorkoutData(UUID.randomUUID().toString());
        profile.setData(data);
        return profile;
    }

    private String profile2Json(final UserProfile userProfile) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userProfile);
    }
}
