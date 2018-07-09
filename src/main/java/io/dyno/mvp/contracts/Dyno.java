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
import org.web3j.abi.datatypes.generated.Bytes16;
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
    private static final String BINARY = "60806040523480156200001157600080fd5b5060023390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050600380600181540180825580915050906001820390600052602060002090600291828204019190066010027f73656c6600000000000000000000000000000000000000000000000000000000909190916101000a8154816fffffffffffffffffffffffffffffffff0219169083700100000000000000000000000000000000900402179055505060048060018154018082558091505090600182039060005260206000200160006040805190810160405280600d81526020017f6e6f742d617661696c61626c6500000000000000000000000000000000000000815250909190915090805190602001906200016f92919062000177565b505062000226565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001ba57805160ff1916838001178555620001eb565b82800160010185558215620001eb579182015b82811115620001ea578251825591602001919060010190620001cd565b5b509050620001fa9190620001fe565b5090565b6200022391905b808211156200021f57600081600090555060010162000205565b5090565b90565b61195380620002366000396000f3006080604052600436106100f1576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063026d1170146100f65780630ab7c933146101775780630bac9fe71461023057806316b7addf146102975780631973ef91146102ef57806329f78d8814610346578063402f707e146103ec5780634fd3e6831461048a57806363db68db146104de57806369c212f61461055e5780638de094d51461064e578063a6c4ec0e14610741578063a74380a61461079c578063b5cb15f714610858578063ce10cf8814610883578063ed1a998d146108f0578063ff5d32fe1461096d575b600080fd5b34801561010257600080fd5b5061015d600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a73565b604051808215151515815260200191505060405180910390f35b34801561018357600080fd5b506101b560048036038101908080356fffffffffffffffffffffffffffffffff19169060200190929190505050610afe565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101f55780820151818401526020810190506101da565b50505050905090810190601f1680156102225780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561023c57600080fd5b5061025b60048036038101908080359060200190929190505050610c06565b60405180826fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b3480156102a357600080fd5b506102d560048036038101908080356fffffffffffffffffffffffffffffffff19169060200190929190505050610c64565b604051808215151515815260200191505060405180910390f35b3480156102fb57600080fd5b50610330600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ce6565b6040518082815260200191505060405180910390f35b34801561035257600080fd5b5061037160048036038101908080359060200190929190505050610d42565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156103b1578082015181840152602081019050610396565b50505050905090810190601f1680156103de5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156103f857600080fd5b5061047060048036038101908080356fffffffffffffffffffffffffffffffff19169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610e10565b604051808215151515815260200191505060405180910390f35b34801561049657600080fd5b506104c860048036038101908080356fffffffffffffffffffffffffffffffff19169060200190929190505050610fe7565b6040518082815260200191505060405180910390f35b3480156104ea57600080fd5b5061051c60048036038101908080356fffffffffffffffffffffffffffffffff1916906020019092919050505061103e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561056a57600080fd5b5061059f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506110ce565b60405180848152602001836fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200180602001828103825283818151815260200191508051906020019080838360005b838110156106115780820151818401526020810190506105f6565b50505050905090810190601f16801561063e5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561065a57600080fd5b5061068c60048036038101908080356fffffffffffffffffffffffffffffffff191690602001909291905050506112a5565b604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b838110156107045780820151818401526020810190506106e9565b50505050905090810190601f1680156107315780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561074d57600080fd5b50610782600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611465565b604051808215151515815260200191505060405180910390f35b3480156107a857600080fd5b506107dd600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061151f565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561081d578082015181840152602081019050610802565b50505050905090810190601f16801561084a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561086457600080fd5b5061086d61162c565b6040518082815260200191505060405180910390f35b34801561088f57600080fd5b506108ae60048036038101908080359060200190929190505050611639565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156108fc57600080fd5b50610931600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061168f565b60405180826fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b34801561097957600080fd5b506109986004803603810190808035906020019092919050505061172c565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001836fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610a36578082015181840152602081019050610a1b565b50505050905090810190601f168015610a635780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6000610a7e33611465565b1515610a8957600080fd5b8160046000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054815481101515610ad757fe5b906000526020600020019080519060200190610af4929190611882565b5060019050919050565b6060610b0982610c64565b1515610b1457600080fd5b600460016000846fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200190815260200160002054815481101515610b5c57fe5b906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bfa5780601f10610bcf57610100808354040283529160200191610bfa565b820191906000526020600020905b815481529060010190602001808311610bdd57829003601f168201915b50505050509050919050565b600060028054905082101515610c1b57600080fd5b600382815481101515610c2a57fe5b90600052602060002090600291828204019190066010029054906101000a9004700100000000000000000000000000000000029050919050565b60008060016000846fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff19168152602001908152602001600020541180610cdf57507f73656c6600000000000000000000000000000000000000000000000000000000826fffffffffffffffffffffffffffffffff1916145b9050919050565b6000610cf182611465565b1515610cfc57600080fd5b6000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b606060028054905082101515610d5757600080fd5b600482815481101515610d6657fe5b906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e045780601f10610dd957610100808354040283529160200191610e04565b820191906000526020600020905b815481529060010190602001808311610de757829003601f168201915b50505050509050919050565b6000610e1b33611465565b151515610e2757600080fd5b610e3083610c64565b151515610e3c57600080fd5b60023390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050600383908060018154018082558091505090600182039060005260206000209060029182820401919006601002909192909190916101000a8154816fffffffffffffffffffffffffffffffff021916908370010000000000000000000000000000000090040217905550506004829080600181540180825580915050906001820390600052602060002001600090919290919091509080519060200190610f4a929190611882565b50506001600280549050036000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060016002805490500360016000856fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff19168152602001908152602001600020819055506001905092915050565b6000610ff282610c64565b1515610ffd57600080fd5b60016000836fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff19168152602001908152602001600020549050919050565b600061104982610c64565b151561105457600080fd5b600260016000846fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff191681526020019081526020016000205481548110151561109c57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6000806060600280549050831015156110e657600080fd5b6000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205460036000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205481548110151561117257fe5b90600052602060002090600291828204019190066010029054906101000a90047001000000000000000000000000000000000260046000808873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548154811015156111f257fe5b90600052602060002001808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112915780601f1061126657610100808354040283529160200191611291565b820191906000526020600020905b81548152906001019060200180831161127457829003601f168201915b505050505090509250925092509193909250565b6000806060600280549050831015156112bd57600080fd5b60016000856fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff1916815260200190815260200160002054600260016000876fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff191681526020019081526020016000205481548110151561133f57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600460016000886fffffffffffffffffffffffffffffffff19166fffffffffffffffffffffffffffffffff19168152602001908152602001600020548154811015156113b257fe5b90600052602060002001808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114515780601f1061142657610100808354040283529160200191611451565b820191906000526020600020905b81548152906001019060200180831161143457829003601f168201915b505050505090509250925092509193909250565b6000806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411806115185750600260008154811015156114be57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16145b9050919050565b606061152a82611465565b151561153557600080fd5b60046000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205481548110151561158257fe5b906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116205780601f106115f557610100808354040283529160200191611620565b820191906000526020600020905b81548152906001019060200180831161160357829003601f168201915b50505050509050919050565b6000600280549050905090565b60006002805490508210151561164e57600080fd5b60028281548110151561165d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600061169a82611465565b15156116a557600080fd5b60036000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548154811015156116f257fe5b90600052602060002090600291828204019190066010029054906101000a9004700100000000000000000000000000000000029050919050565b60008060606002805490508410151561174457600080fd5b60028481548110151561175357fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038581548110151561178d57fe5b90600052602060002090600291828204019190066010029054906101000a9004700100000000000000000000000000000000026004868154811015156117cf57fe5b90600052602060002001808054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561186e5780601f106118435761010080835404028352916020019161186e565b820191906000526020600020905b81548152906001019060200180831161185157829003601f168201915b505050505090509250925092509193909250565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106118c357805160ff19168380011785556118f1565b828001600101855582156118f1579182015b828111156118f05782518255916020019190600101906118d5565b5b5090506118fe9190611902565b5090565b61192491905b80821115611920576000816000905550600101611908565b5090565b905600a165627a7a723058200ad5792f16986c6cf0051fde31c5ac3096ac6e8cbfe98fc7ea675692852a4fd10029";

    public static final String FUNC_UPDATEUSER = "updateUser";

    public static final String FUNC_GETIPFSHASHBYUSERNAME = "getIpfsHashByUsername";

    public static final String FUNC_GETUSERNAMEBYINDEX = "getUsernameByIndex";

    public static final String FUNC_USERNAMETAKEN = "usernameTaken";

    public static final String FUNC_GETINDEXBYADDRESS = "getIndexByAddress";

    public static final String FUNC_GETIPFSHASHBYINDEX = "getIpfsHashByIndex";

    public static final String FUNC_CREATEUSER = "createUser";

    public static final String FUNC_GETINDEXBYUSERNAME = "getIndexByUsername";

    public static final String FUNC_GETADDRESSBYUSERNAME = "getAddressByUsername";

    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";

    public static final String FUNC_GETUSERBYUSERNAME = "getUserByUsername";

    public static final String FUNC_HASUSER = "hasUser";

    public static final String FUNC_GETIPFSHASHBYADDRESS = "getIpfsHashByAddress";

    public static final String FUNC_GETUSERCOUNT = "getUserCount";

    public static final String FUNC_GETADDRESSBYINDEX = "getAddressByIndex";

    public static final String FUNC_GETUSERNAMEBYADDRESS = "getUsernameByAddress";

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

    public RemoteCall<byte[]> getIpfsHashByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETIPFSHASHBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> getUsernameByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETUSERNAMEBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes16>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Boolean> usernameTaken(byte[] username) {
        final Function function = new Function(FUNC_USERNAMETAKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteCall<TransactionReceipt> createUser(byte[] username, byte[] ipfsHash) {
        final Function function = new Function(
                FUNC_CREATEUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username), 
                new org.web3j.abi.datatypes.DynamicBytes(ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getIndexByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETINDEXBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getAddressByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETADDRESSBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple3<BigInteger, byte[], byte[]>> getUserByAddress(String userAddress) {
        final Function function = new Function(FUNC_GETUSERBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes16>() {}, new TypeReference<DynamicBytes>() {}));
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

    public RemoteCall<Tuple3<BigInteger, String, byte[]>> getUserByUsername(byte[] username) {
        final Function function = new Function(FUNC_GETUSERBYUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes16(username)), 
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
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes16>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple3<String, byte[], byte[]>> getUserByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETUSERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes16>() {}, new TypeReference<DynamicBytes>() {}));
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
