package io.dyno.mvp.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Dyno extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b5060023390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060038060018154018082558091505090600182039060005260206000200160007f73656c66000000000000000000000000000000000000000000000000000000009091909150906000191690555060048060018154018082558091505090600182039060005260206000200160006040805190810160405280600d81526020017f6e6f742d617661696c61626c650000000000000000000000000000000000000081525090919091509080519060200190620001369291906200013e565b5050620001ed565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200018157805160ff1916838001178555620001b2565b82800160010185558215620001b2579182015b82811115620001b157825182559160200191906001019062000194565b5b509050620001c19190620001c5565b5090565b620001ea91905b80821115620001e6576000816000905550600101620001cc565b5090565b90565b6116a980620001fd6000396000f3006080604052600436106100f1576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063026d1170146100f657806306f42530146101775780630bac9fe7146101e85780631973ef911461023157806329f78d88146102885780634aa5fd411461032e57806369c212f6146103d85780636fcdca72146104aa578063841ca731146104f357806389c5740714610582578063a6c4ec0e146105c7578063a74380a614610622578063b5cb15f7146106de578063ce10cf8814610709578063ed1a998d14610776578063fe2da970146107d5578063ff5d32fe146108b9575b600080fd5b34801561010257600080fd5b5061015d600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506109a1565b604051808215151515815260200191505060405180910390f35b34801561018357600080fd5b506101a66004803603810190808035600019169060200190929190505050610a2c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101f457600080fd5b5061021360048036038101908080359060200190929190505050610a9e565b60405180826000191660001916815260200191505060405180910390f35b34801561023d57600080fd5b50610272600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ad4565b6040518082815260200191505060405180910390f35b34801561029457600080fd5b506102b360048036038101908080359060200190929190505050610b30565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102f35780820151818401526020810190506102d8565b50505050905090810190601f1680156103205780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561033a57600080fd5b5061035d6004803603810190808035600019169060200190929190505050610bfe565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561039d578082015181840152602081019050610382565b50505050905090810190601f1680156103ca5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156103e457600080fd5b50610419600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ce8565b60405180848152602001836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561046d578082015181840152602081019050610452565b50505050905090810190601f16801561049a5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b3480156104b657600080fd5b506104d96004803603810190808035600019169060200190929190505050610e97565b604051808215151515815260200191505060405180910390f35b3480156104ff57600080fd5b506105686004803603810190808035600019169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610eec565b604051808215151515815260200191505060405180910390f35b34801561058e57600080fd5b506105b1600480360381019080803560001916906020019092919050505061106c565b6040518082815260200191505060405180910390f35b3480156105d357600080fd5b50610608600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506110a5565b604051808215151515815260200191505060405180910390f35b34801561062e57600080fd5b50610663600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061115f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156106a3578082015181840152602081019050610688565b50505050905090810190601f1680156106d05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156106ea57600080fd5b506106f361126c565b6040518082815260200191505060405180910390f35b34801561071557600080fd5b5061073460048036038101908080359060200190929190505050611279565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561078257600080fd5b506107b7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506112cf565b60405180826000191660001916815260200191505060405180910390f35b3480156107e157600080fd5b506108046004803603810190808035600019169060200190929190505050611344565b604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561087c578082015181840152602081019050610861565b50505050905090810190601f1680156108a95780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b3480156108c557600080fd5b506108e4600480360381019080803590602001909291905050506114aa565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610964578082015181840152602081019050610949565b50505050905090810190601f1680156109915780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b60006109ac336110a5565b15156109b757600080fd5b8160046000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054815481101515610a0557fe5b906000526020600020019080519060200190610a229291906115d8565b5060019050919050565b6000610a3782610e97565b1515610a4257600080fd5b600260016000846000191660001916815260200190815260200160002054815481101515610a6c57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600060028054905082101515610ab357600080fd5b600382815481101515610ac257fe5b90600052602060002001549050919050565b6000610adf826110a5565b1515610aea57600080fd5b6000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b606060028054905082101515610b4557600080fd5b600482815481101515610b5457fe5b906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bf25780601f10610bc757610100808354040283529160200191610bf2565b820191906000526020600020905b815481529060010190602001808311610bd557829003601f168201915b50505050509050919050565b6060610c0982610e97565b1515610c1457600080fd5b600460016000846000191660001916815260200190815260200160002054815481101515610c3e57fe5b906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cdc5780601f10610cb157610100808354040283529160200191610cdc565b820191906000526020600020905b815481529060010190602001808311610cbf57829003601f168201915b50505050509050919050565b600080606060028054905083101515610d0057600080fd5b6000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205460036000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054815481101515610d8c57fe5b906000526020600020015460046000808873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054815481101515610de457fe5b90600052602060002001808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e835780601f10610e5857610100808354040283529160200191610e83565b820191906000526020600020905b815481529060010190602001808311610e6657829003601f168201915b505050505090509250925092509193909250565b600080600160008460001916600019168152602001908152602001600020541180610ee557507f73656c66000000000000000000000000000000000000000000000000000000008260001916145b9050919050565b6000610ef7336110a5565b151515610f0357600080fd5b610f0c83610e97565b151515610f1857600080fd5b60023390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060038390806001815401808255809150509060018203906000526020600020016000909192909190915090600019169055506004829080600181540180825580915050906001820390600052602060002001600090919290919091509080519060200190610fed9291906115d8565b50506001600280549050036000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600160028054905003600160008560001916600019168152602001908152602001600020819055506001905092915050565b600061107782610e97565b151561108257600080fd5b600160008360001916600019168152602001908152602001600020549050919050565b6000806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411806111585750600260008154811015156110fe57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16145b9050919050565b606061116a826110a5565b151561117557600080fd5b60046000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548154811015156111c257fe5b906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112605780601f1061123557610100808354040283529160200191611260565b820191906000526020600020905b81548152906001019060200180831161124357829003601f168201915b50505050509050919050565b6000600280549050905090565b60006002805490508210151561128e57600080fd5b60028281548110151561129d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60006112da826110a5565b15156112e557600080fd5b60036000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205481548110151561133257fe5b90600052602060002001549050919050565b60008060606002805490508310151561135c57600080fd5b600160008560001916600019168152602001908152602001600020546002600160008760001916600019168152602001908152602001600020548154811015156113a257fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166004600160008860001916600019168152602001908152602001600020548154811015156113f757fe5b90600052602060002001808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114965780601f1061146b57610100808354040283529160200191611496565b820191906000526020600020905b81548152906001019060200180831161147957829003601f168201915b505050505090509250925092509193909250565b6000806060600280549050841015156114c257600080fd5b6002848154811015156114d157fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038581548110151561150b57fe5b906000526020600020015460048681548110151561152557fe5b90600052602060002001808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115c45780601f10611599576101008083540402835291602001916115c4565b820191906000526020600020905b8154815290600101906020018083116115a757829003601f168201915b505050505090509250925092509193909250565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061161957805160ff1916838001178555611647565b82800160010185558215611647579182015b8281111561164657825182559160200191906001019061162b565b5b5090506116549190611658565b5090565b61167a91905b8082111561167657600081600090555060010161165e565b5090565b905600a165627a7a72305820369c412ac43dc09315d8256fb8b06304b91d72f50b2ff9efb2fb46e37faa51720029";

    public static final String FUNC_UPDATEUSER = "updateUser";

    public static final String FUNC_GETADDRESSBYUSERNAME = "getAddressByUsername";

    public static final String FUNC_GETUSERNAMEBYINDEX = "getUsernameByIndex";

    public static final String FUNC_GETINDEXBYADDRESS = "getIndexByAddress";

    public static final String FUNC_GETIPFSHASHBYINDEX = "getIpfsHashByIndex";

    public static final String FUNC_GETIPFSHASHBYUSERNAME = "getIpfsHashByUsername";

    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";

    public static final String FUNC_USERNAMETAKEN = "usernameTaken";

    public static final String FUNC_CREATEUSER = "createUser";

    public static final String FUNC_GETINDEXBYUSERNAME = "getIndexByUsername";

    public static final String FUNC_HASUSER = "hasUser";

    public static final String FUNC_GETIPFSHASHBYADDRESS = "getIpfsHashByAddress";

    public static final String FUNC_GETUSERCOUNT = "getUserCount";

    public static final String FUNC_GETADDRESSBYINDEX = "getAddressByIndex";

    public static final String FUNC_GETUSERNAMEBYADDRESS = "getUsernameByAddress";

    public static final String FUNC_GETUSERBYUSERNAME = "getUserByUsername";

    public static final String FUNC_GETUSERBYINDEX = "getUserByIndex";

    protected Dyno(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Dyno(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> updateUser(byte[] ipfsHash) {
        final Function function = new Function(
                FUNC_UPDATEUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getAddressByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETADDRESSBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> getUsernameByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETUSERNAMEBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> getIndexByAddress(String userAddress) {
        final Function function = new Function(FUNC_GETINDEXBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> getIpfsHashByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETIPFSHASHBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> getIpfsHashByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETIPFSHASHBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple3<BigInteger, byte[], byte[]>> getUserByAddress(String userAddress) {
        final Function function = new Function(FUNC_GETUSERBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple3<BigInteger, byte[], byte[]>>(
                new Callable<Tuple3<BigInteger, byte[], byte[]>>() {
                    @Override
                    public Tuple3<BigInteger, byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, byte[], byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Boolean> usernameTaken(byte[] username) {
        final Function function = new Function(FUNC_USERNAMETAKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> createUser(byte[] username, byte[] ipfsHash) {
        final Function function = new Function(
                FUNC_CREATEUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username), 
                new org.web3j.abi.datatypes.DynamicBytes(ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getIndexByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETINDEXBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> hasUser(String userAddress) {
        final Function function = new Function(FUNC_HASUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<byte[]> getIpfsHashByAddress(String userAddress) {
        final Function function = new Function(FUNC_GETIPFSHASHBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> getUserCount() {
        final Function function = new Function(FUNC_GETUSERCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getAddressByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETADDRESSBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> getUsernameByAddress(String userAddress) {
        final Function function = new Function(FUNC_GETUSERNAMEBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple3<BigInteger, String, byte[]>> getUserByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETUSERBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, byte[]>>(
                new Callable<Tuple3<BigInteger, String, byte[]>>() {
                    @Override
                    public Tuple3<BigInteger, String, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<String, byte[], byte[]>> getUserByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETUSERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple3<String, byte[], byte[]>>(
                new Callable<Tuple3<String, byte[], byte[]>>() {
                    @Override
                    public Tuple3<String, byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, byte[], byte[]>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public static RemoteCall<Dyno> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dyno.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Dyno> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dyno.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Dyno load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dyno(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Dyno load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dyno(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
