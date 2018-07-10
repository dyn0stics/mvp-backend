package io.dyno.mvp;

import io.dyno.mvp.contracts.Dyno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.web3j.abi.datatypes.generated.Bytes16;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@PropertySource("application.properties")
@ComponentScan
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final String CONTRACT_ADDRESS = "0x35F7164130841A5b4e58D07966Eb125C65184398";
    private static final String USER_PK = "aa0f99731ec0ee728888b64407f2269145a87551031d2d001bc1fefa6b4d087d";
    private static final String NODE_URL = "http://127.0.0.1:7545";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

       // final List<String> accounts = web3j.ethAccounts().send().getAccounts();
      //  log.info("Wallet accounts: " + Arrays.toString(accounts.toArray()));


        final Credentials credentials = Credentials.create(USER_PK);

      //  final Dyno contract = Dyno.load(CONTRACT_ADDRESS, web3j, credentials, new BigInteger("20000000000"), new BigInteger("6721975"));

        //  final TransactionReceipt transactionReceipt = contract.createUser(stringToBytes16(toHex("dyno")).getValue(), stringToBytes16(toHex("rocks")).getValue()).send();


     //   log.info("Count: " + contract.getUsernameByIndex(new BigInteger("1")).send());
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
