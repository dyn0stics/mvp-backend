package io.dyno.mvp;

import io.dyno.mvp.contracts.Dyno;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class MvpApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MvpApplicationTests.class);

    private String NODE_URL = "http://127.0.0.1:7545";
    final String CONTRACT_ADDRESS = "0x501dbf48d1e29179e395dbaa8896d9ff0bacbff8";

    @Test
    public void transferFunds() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final String USER_PK = "3da1641b555265f0589c7f30b4f32ec316498ffcb4bf72d616f8439031c3da7d";
        final Credentials credentials = Credentials.create(USER_PK);
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, "0x8316c49dcdcf7912d7494f1964c60924f7d74114", BigDecimal.valueOf(0.001), Convert.Unit.ETHER).send();
    }

    @Test
    public void getUsernameByAddress() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final String USER_PK = "aa0f99731ec0ee728888b64407f2269145a87551031d2d001bc1fefa6b4d087d";
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
        final String USER_PK = "0bce4acc7746168cf69f30b2d443b5d6594f41875b1e49ebfe5b63b0d5cf26b3";
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



    public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }
}
