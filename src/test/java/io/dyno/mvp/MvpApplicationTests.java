package io.dyno.mvp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dyno.mvp.contracts.DynoMarket;
import io.dyno.mvp.crypto.ECIESCoder;
import io.dyno.mvp.crypto.ECKey;
import io.dyno.mvp.model.UserData;
import io.dyno.mvp.model.UserProfile;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.bouncycastle.crypto.parsers.ECIESPublicKeyParser;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.math.ec.ECPoint;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
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
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MvpApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MvpApplicationTests.class);

    private String NODE_URL = "http://127.0.0.1:7545";
    final String CONTRACT_ADDRESS = "0x27f9534bf05e4d2d2e5037f15646800b38fe10cf";
    final String TOKEN_ADDRESS = "0x1180c8537e898c27773062e9bdf17cdb47ffe95c";
    final String USER_PK = "c28463ee0cb84ffdabb1edabf10d24497d7e3e562657d21ac64f1855fb1a6d6a";

    @Test
    public void deployContract() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final Credentials credentials = Credentials.create(USER_PK);
        final DynoMarket dyno = DynoMarket.deploy(web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"), TOKEN_ADDRESS).send();
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
        final DynoMarket contract = DynoMarket.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

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
        final DynoMarket contract = DynoMarket.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

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

        Multihash filePointer2 = Multihash.fromBase58("QmekPMJAts4MwAoWk6AQwyhgcsAQz7tv58hfhXgxRnCWZB");
        byte[] fileContents2 = ipfs.cat(filePointer2);
        log.info("Retreived: " + new String(fileContents2));
    }


    @Test
    public void bigChainDBTest() {
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("app_id", "170a69eb");
        headers.set("app_key", "0d0e82caaf977e317f2765566a9ef029");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> respEntity = restTemplate.exchange("https://test.bigchaindb.com/api/v1/assets/?search=bigchaindb", HttpMethod.GET, entity, String.class);
        log.info(respEntity.getBody());
    }

    @Test // decrypt cpp data
    public void test1() {
        BigInteger privKey = new BigInteger("5e173f6ac3c669587538e7727cf19b782a4f2fda07c1eaa662c593e5e85e3051", 16);
        byte[] cipher = Hex.decode("049934a7b2d7f9af8fd9db941d9da281ac9381b5740e1f64f7092f3588d4f87f5ce55191a6653e5e80c1c5dd538169aa123e70dc6ffc5af1827e546c0e958e42dad355bcc1fcb9cdf2cf47ff524d2ad98cbf275e661bf4cf00960e74b5956b799771334f426df007350b46049adb21a6e78ab1408d5e6ccde6fb5e69f0f4c92bb9c725c02f99fa72b9cdc8dd53cff089e0e73317f61cc5abf6152513cb7d833f09d2851603919bf0fbe44d79a09245c6e8338eb502083dc84b846f2fee1cc310d2cc8b1b9334728f97220bb799376233e113");

        byte[] payload = new byte[0];
        try {
            payload = ECIESCoder.decrypt(privKey, cipher);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Assert.assertEquals("802b052f8b066640bba94a4fc39d63815c377fced6fcb84d27f791c9921ddf3e9bf0108e298f490812847109cbd778fae393e80323fd643209841a3b7f110397f37ec61d84cea03dcc5e8385db93248584e8af4b4d1c832d8c7453c0089687a700",
                Hex.toHexString(payload));
    }


    @Test  // encrypt decrypt round trip
    public void test2() {

        BigInteger privKey = new BigInteger("5e173f6ac3c669587538e7727cf19b782a4f2fda07c1eaa662c593e5e85e3051", 16);

        byte[] payload = Hex.decode("1122334455");

        ECKey ecKey = ECKey.fromPrivate(privKey);
        ECPoint pubKeyPoint = ecKey.getPubKeyPoint();

        byte[] cipher = new byte[0];
        try {
            cipher = ECIESCoder.encrypt(pubKeyPoint, payload);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println(Hex.toHexString(cipher));

        byte[] decrypted_payload = new byte[0];
        try {
            decrypted_payload = ECIESCoder.decrypt(privKey, cipher);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println(Hex.toHexString(decrypted_payload));
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
