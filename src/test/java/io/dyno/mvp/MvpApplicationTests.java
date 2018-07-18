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
import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;
import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Hex;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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


    @Test
    public void encryptionTest() throws NoSuchPaddingException, NoSuchAlgorithmException {
        final String dataString = "TEST";
        final String privateKey = "441ef9e68cd2960004213a76600e14c69de9a5c2e8d480bfe0fad45b9bd0c972";
        final byte[] base64String = Base64.getEncoder().encode(dataString.getBytes());
        log.info("Base64: " + new String(base64String));
        final String randomSecret = UUID.randomUUID().toString();
        final String encryptedSecret = privateToPublic(Hex.toHexString(privateKey.getBytes()));
        log.info("Secret " + randomSecret);
        log.info("EncryptedSecret " + encryptedSecret);
        Cipher acipher = Cipher.getInstance("ECIES");
        //  generate derivation and encoding vectors
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        IESParameterSpec param = new IESParameterSpec(d, e, 256);

        // encrypt the plaintext using the public key
       // acipher.init(Cipher.ENCRYPT_MODE, new IEKeySpec(Hex.toHexString(privateKey.getBytes()), privateToPublic(Hex.toHexString(privateKey.getBytes())), param);
        final String encrypted =  acipher.doFinal();
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

    private String privateToPublic(final String privateKeyInHex) {
        BigInteger privateKeyInBT = new BigInteger(privateKeyInHex, 16);

        ECKeyPair aPair = ECKeyPair.create(privateKeyInBT);

        BigInteger publicKeyInBT = aPair.getPublicKey();

        String sPublickeyInHex = publicKeyInBT.toString(16);
        return sPublickeyInHex;
    }
}
