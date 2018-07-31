package io.dyno.mvp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dyno.mvp.contracts.Dyno;
import io.dyno.mvp.controller.SystemController;
import io.dyno.mvp.crypto.ECIESCoder;
import io.dyno.mvp.crypto.ECKey;
import io.dyno.mvp.model.UserData;
import io.dyno.mvp.model.UserProfile;
import io.dyno.mvp.service.UserService;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.math.ec.ECPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    Web3j web3j;
    @Value("${dyno.contract.address}")
    private String CONTRACT_ADDRESS;
    @Value("${dyno.contract.fundsAccount}")
    private String FUNDS_ACCOUNT;
    @Value("${ipfs.node.ip}")
    private String NODE_IP;
    @Value("${demo.file}")
    private String DEMO_FILE;
    @Autowired
    IPFS ipfs;
    private final String GAS_PRICE = "20000000000";
    private final String GAS_LIMIT = "6721975";

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
        final Credentials fundsAccount = Credentials.create(FUNDS_ACCOUNT);
        Transfer.sendFunds(web3j, fundsAccount, credentials.getAddress(), BigDecimal.valueOf(1), Convert.Unit.ETHER).send();
        final UserData data = new UserData();
        data.setAge("21");
        data.setCountry("Germany");
        data.setGender("male");
        data.setWeight("80");
        final String workoutData = readFileAsString(DEMO_FILE);
        log.info("Workout data:" + workoutData.substring(100));
        data.setWorkoutData(workoutData);
        userProfile.setData(data);


        final byte[] payload = data.getWorkoutData().getBytes();
        final ECKey ecKey = ECKey.fromPrivate(ecKeyPair.getPrivateKey());
        final ECPoint pubKeyPoint = ecKey.getPubKeyPoint();
        byte[] cipher = new byte[0];
        try {
            cipher = ECIESCoder.encrypt(pubKeyPoint, payload);
        } catch (Throwable e) {
            log.error("Error during encryption: " + e.getMessage());
        }

        log.info("Workout data encrypted: " + Hex.toHexString(cipher).substring(100));
        data.setWorkoutData(Hex.toHexString(cipher));
        // Test method END (set initial account balance and 1 workout record)

        final NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("data", profile2Json(userProfile).getBytes());
        final MerkleNode addResult = ipfs.add(file).get(0);
        log.info("Saved IPFS hash: " + addResult.hash.toBase58());
        userProfile.setIpfsHash(addResult.hash.toBase58());
        final EthGetBalance ethGetBalance = web3j
                .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        log.info("Balance: " + ethGetBalance.getBalance());
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger(GAS_PRICE), new BigInteger(GAS_LIMIT));
        contract.createUser(stringToBytes32(userProfile.getUsername()).getValue(), userProfile.getIpfsHash().getBytes()).send();
        log.info("Profile created for address " + credentials.getAddress() + " username " + userProfile.getUsername());
        return userProfile;
    }

    @Override
    public UserProfile getUser(final String privateKey) throws Exception {
        final Credentials credentials = Credentials.create(privateKey);
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger(GAS_PRICE), new BigInteger(GAS_LIMIT));
        final String ipfsHash = new String(contract.getIpfsHashByAddress(credentials.getAddress()).send());
        final Multihash filePointer = Multihash.fromBase58(ipfsHash);
        final byte[] fileContents = ipfs.cat(filePointer);
        log.info("Retreived: " + new String(fileContents).substring(0, 100));
        final ObjectMapper objectMapper = new ObjectMapper();
        final UserProfile profile = objectMapper.readValue(new String(fileContents), UserProfile.class);
        final BigInteger privKey = new BigInteger(privateKey, 16);
        byte[] payload = new byte[0];
        try {
            log.info("Decrypting ..." + profile.getData().getWorkoutData().substring(0, 100));
            payload = ECIESCoder.decrypt(privKey, Hex.decode(profile.getData().getWorkoutData()));
        } catch (Throwable e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        profile.getData().setWorkoutData(new String(payload)); // show decrypted data only for users with PK
        profile.setIpfsHash(NODE_IP + ":8080/ipfs/" + ipfsHash); //http://localhost:8080/ipfs/Qmcr5S89hSc5GLn6TJyHR2KCZ8DWucfSaHkAA6U8UEsMSW
        return profile;
    }

    @Override
    public List<UserProfile> doSearch(String param) throws Exception {
        final Credentials credentials = Credentials.create(FUNDS_ACCOUNT);
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger(GAS_PRICE), new BigInteger(GAS_LIMIT));
        final BigInteger nrOfUsers = contract.getUserCount().send();
        log.info("Number of users detected: " + nrOfUsers);
        List<UserProfile> userProfiles = new ArrayList<>();
        for (BigInteger bi = BigInteger.valueOf(0);
             nrOfUsers.compareTo(bi) > 0;
             bi = bi.add(BigInteger.ONE)) {
            log.info("Fetch user index: " + bi);
            final UserProfile profile = new UserProfile();
            profile.setAddress(contract.getAddressByIndex(bi).send());
            profile.setIpfsHash(new String(contract.getIpfsHashByIndex(bi).send()));
            profile.setUsername(new String(contract.getUsernameByIndex(bi).send()));
            log.info("Fetch user complete: " + profile.getAddress() + " " + profile.getIpfsHash());
            userProfiles.add(profile);
        }
        return userProfiles;
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

    private String getFile(final String fileName) {
        final StringBuilder result = new StringBuilder("");
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
