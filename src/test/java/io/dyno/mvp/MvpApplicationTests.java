package io.dyno.mvp;

import io.dyno.mvp.contracts.Dyno;
import io.dyno.mvp.controller.SystemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.abi.datatypes.generated.Bytes16;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class MvpApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MvpApplicationTests.class);

    private String NODE_URL = "http://127.0.0.1:7545";

    @Test
    public void transferFunds() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final String USER_PK = "aa0f99731ec0ee728888b64407f2269145a87551031d2d001bc1fefa6b4d087d";
        final Credentials credentials = Credentials.create(USER_PK);
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, "0x8316c49dcdcf7912d7494f1964c60924f7d74114", BigDecimal.valueOf(0.001), Convert.Unit.ETHER).send();
    }

    @Test
    public void createUserContract() throws Exception {
        final String CONTRACT_ADDRESS = "0x35F7164130841A5b4e58D07966Eb125C65184398";
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        final String USER_PK = "35b4c9947babd2b667e3de697a70db45eb832ffa2ad07341f776950dc6997bca";
        final Credentials credentials = Credentials.create(USER_PK);
        log.info("Address: " + credentials.getAddress());
        EthGetBalance ethGetBalance = web3j
                .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        log.info("Balance: " + ethGetBalance.getBalance());
        final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

        contract.createUser(stringToBytes16(toHex("uio")).getValue(), stringToBytes16(toHex("uyi")).getValue()).send();
        log.info("Number of users: " + contract.getUserCount().send().intValue());
    }


    private static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }

    public static Bytes16 stringToBytes16(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[16];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, 16);
        return new Bytes16(byteValueLen32);
    }
}
