package io.dyno.mvp.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
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
import org.web3j.tuples.generated.Tuple5;
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
public class DynoMarket extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b506040516020806200159683398101604081815291516002805460018181019092557f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace018054600160a060020a03191633179055600380548083019091557f73656c66000000000000000000000000000000000000000000000000000000007fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b9091015560048054918201808255600091909152848401909452600d8084527f6e6f742d617661696c61626c650000000000000000000000000000000000000060209094019384529193926200012c927f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b9092019162000154565b505060068054600160a060020a031916600160a060020a0392909216919091179055620001f9565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200019757805160ff1916838001178555620001c7565b82800160010185558215620001c7579182015b82811115620001c7578251825591602001919060010190620001aa565b50620001d5929150620001d9565b5090565b620001f691905b80821115620001d55760008155600101620001e0565b90565b61138d80620002096000396000f3006080604052600436106101275763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663026d1170811461012c57806306f42530146101995780630bac9fe7146101cd5780631973ef91146101f757806329f78d88146102185780634aa5fd41146102a557806369c212f6146102bd5780636fcdca721461036657806373c2dc121461037e578063841ca7311461039357806389c57407146103f1578063a6c4ec0e14610409578063a74380a61461042a578063b5cb15f71461044b578063ce10cf8814610460578063decbef6014610478578063e359a0a1146104e1578063e5a6b10f14610610578063e887307514610625578063ed1a998d14610683578063fe2da970146106a4578063ff5d32fe14610716575b600080fd5b34801561013857600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101859436949293602493928401919081908401838280828437509497506107819650505050505050565b604080519115158252519081900360200190f35b3480156101a557600080fd5b506101b16004356107dc565b60408051600160a060020a039092168252519081900360200190f35b3480156101d957600080fd5b506101e560043561082b565b60408051918252519081900360200190f35b34801561020357600080fd5b506101e5600160a060020a036004351661085c565b34801561022457600080fd5b5061023060043561088e565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561026a578181015183820152602001610252565b50505050905090810190601f1680156102975780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102b157600080fd5b50610230600435610947565b3480156102c957600080fd5b506102de600160a060020a036004351661097b565b60408051848152602080820185905260609282018381528451938301939093528351919291608084019185019080838360005b83811015610329578181015183820152602001610311565b50505050905090810190601f1680156103565780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561037257600080fd5b50610185600435610a9c565b34801561038a57600080fd5b506101e5610add565b34801561039f57600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610185958335953695604494919390910191908190840183828082843750949750610ae49650505050505050565b3480156103fd57600080fd5b506101e5600435610c05565b34801561041557600080fd5b50610185600160a060020a0360043516610c2e565b34801561043657600080fd5b50610230600160a060020a0360043516610c7f565b34801561045757600080fd5b506101e5610cbd565b34801561046c57600080fd5b506101b1600435610cc3565b34801561048457600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526101e5958335600160a060020a03169536956044949193909101919081908401838280828437509497505093359450610ce29350505050565b3480156104ed57600080fd5b506104f9600435610e21565b6040518086600160a060020a0316600160a060020a0316815260200185600160a060020a0316600160a060020a031681526020018060200184815260200180602001838103835286818151815260200191508051906020019080838360005b83811015610570578181015183820152602001610558565b50505050905090810190601f16801561059d5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156105d05781810151838201526020016105b8565b50505050905090810190601f1680156105fd5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561061c57600080fd5b506101b1611026565b34801561063157600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526101859583359536956044949193909101919081908401838280828437509497506110359650505050505050565b34801561068f57600080fd5b506101e5600160a060020a03600435166111bd565b3480156106b057600080fd5b506106bc6004356111fb565b6040518084815260200183600160a060020a0316600160a060020a03168152602001806020018281038252838181518152602001915080519060200190808383600083811015610329578181015183820152602001610311565b34801561072257600080fd5b5061072e600435611265565b60408051600160a060020a03851681526020808201859052606092820183815284519383019390935283519192916080840191850190808383600083811015610329578181015183820152602001610311565b600061078c33610c2e565b151561079757600080fd5b336000908152602081905260409020546004805484929081106107b657fe5b9060005260206000200190805190602001906107d39291906112c9565b50600192915050565b60006107e782610a9c565b15156107f257600080fd5b60008281526001602052604090205460028054909190811061081057fe5b600091825260209091200154600160a060020a031692915050565b600254600090821061083c57600080fd5b600380548390811061084a57fe5b90600052602060002001549050919050565b600061086782610c2e565b151561087257600080fd5b50600160a060020a031660009081526020819052604090205490565b600254606090821061089f57600080fd5b60048054839081106108ad57fe5b600091825260209182902001805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561093b5780601f106109105761010080835404028352916020019161093b565b820191906000526020600020905b81548152906001019060200180831161091e57829003601f168201915b50505050509050919050565b606061095282610a9c565b151561095d57600080fd5b6000828152600160205260409020546004805490919081106108ad57fe5b6002546000908190606090821061099157600080fd5b600160a060020a03841660009081526020819052604090205460038054829081106109b857fe5b9060005260206000200154600460008088600160a060020a0316600160a060020a03168152602001908152602001600020548154811015156109f657fe5b600091825260209182902001805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529192839190830182828015610a885780601f10610a5d57610100808354040283529160200191610a88565b820191906000526020600020905b815481529060010190602001808311610a6b57829003601f168201915b505050505090509250925092509193909250565b600081815260016020526040812054811080610ad757507f73656c660000000000000000000000000000000000000000000000000000000082145b92915050565b6005545b90565b6000610aef33610c2e565b15610af957600080fd5b610b0283610a9c565b15610b0c57600080fd5b6002805460018181019092557f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace01805473ffffffffffffffffffffffffffffffffffffffff191633179055600380548083019091557fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b018490556004805491820180825560009190915283519091610bcd917f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b9091019060208601906112c9565b505060028054336000908152602081815260408083206000199485019055935496825260019081905292902094019093555090919050565b6000610c1082610a9c565b1515610c1b57600080fd5b5060009081526001602052604090205490565b600160a060020a038116600090815260208190526040812054811080610ad75750600280546000908110610c5e57fe5b600091825260209091200154600160a060020a038381169116149050919050565b6060610c8a82610c2e565b1515610c9557600080fd5b600160a060020a0382166000908152602081905260409020546004805490919081106108ad57fe5b60025490565b6002546000908210610cd457600080fd5b600280548390811061081057fe5b6040805160a081018252338152600160a060020a0385811660208084019182528385018781526060850187905285518083019096526000808752608086019690965260058054600181018083558289528751919092027f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db08101805492881673ffffffffffffffffffffffffffffffffffffffff1993841617815595517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db1820180549190981692169190911790955590518051919594610de8937f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db2909101929101906112c9565b506060820151600382015560808201518051610e0e9160048401916020909101906112c9565b5050600554600019019695505050505050565b600080606060006060600586815481101515610e3957fe5b60009182526020909120600591820201548154600160a060020a03909116919088908110610e6357fe5b906000526020600020906005020160010160009054906101000a9004600160a060020a0316600588815481101515610e9757fe5b9060005260206000209060050201600201600589815481101515610eb757fe5b90600052602060002090600502016003015460058a815481101515610ed857fe5b9060005260206000209060050201600401828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f7e5780601f10610f5357610100808354040283529160200191610f7e565b820191906000526020600020905b815481529060010190602001808311610f6157829003601f168201915b5050845460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529598508694509250840190508282801561100c5780601f10610fe15761010080835404028352916020019161100c565b820191906000526020600020905b815481529060010190602001808311610fef57829003601f168201915b505050505090509450945094509450945091939590929450565b600654600160a060020a031681565b600060058381548110151561104657fe5b6000918252602090912060059091020160010154600160a060020a0316331461106e57600080fd5b8160058481548110151561107e57fe5b906000526020600020906005020160040190805190602001906110a29291906112c9565b5060065460058054600160a060020a03909216916323b872dd9190869081106110c757fe5b60009182526020909120600591820201548154600160a060020a03909116913391889081106110f257fe5b9060005260206000209060050201600301546040518463ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018084600160a060020a0316600160a060020a0316815260200183600160a060020a0316600160a060020a031681526020018281526020019350505050602060405180830381600087803b15801561118857600080fd5b505af115801561119c573d6000803e3d6000fd5b505050506040513d60208110156111b257600080fd5b506001949350505050565b60006111c882610c2e565b15156111d357600080fd5b600160a060020a03821660009081526020819052604090205460038054909190811061084a57fe5b6002546000908190606090821061121157600080fd5b600084815260016020526040902054600280548290811061122e57fe5b6000918252602080832090910154878352600190915260409091205460048054600160a060020a039093169290919081106109f657fe5b6002546000908190606090841061127b57600080fd5b600280548590811061128957fe5b60009182526020909120015460038054600160a060020a0390921691869081106112af57fe5b90600052602060002001546004868154811015156109f657fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061130a57805160ff1916838001178555611337565b82800160010185558215611337579182015b8281111561133757825182559160200191906001019061131c565b50611343929150611347565b5090565b610ae191905b80821115611343576000815560010161134d5600a165627a7a723058209705cfc04875da54032f6ecda8ca8c2b4e0254b20107cadac97d5422b9e9f5120029";

    public static final String FUNC_UPDATEUSER = "updateUser";

    public static final String FUNC_GETADDRESSBYUSERNAME = "getAddressByUsername";

    public static final String FUNC_GETUSERNAMEBYINDEX = "getUsernameByIndex";

    public static final String FUNC_GETINDEXBYADDRESS = "getIndexByAddress";

    public static final String FUNC_GETIPFSHASHBYINDEX = "getIpfsHashByIndex";

    public static final String FUNC_GETIPFSHASHBYUSERNAME = "getIpfsHashByUsername";

    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";

    public static final String FUNC_USERNAMETAKEN = "usernameTaken";

    public static final String FUNC_GETOFFERSLENGTH = "getOffersLength";

    public static final String FUNC_CREATEUSER = "createUser";

    public static final String FUNC_GETINDEXBYUSERNAME = "getIndexByUsername";

    public static final String FUNC_HASUSER = "hasUser";

    public static final String FUNC_GETIPFSHASHBYADDRESS = "getIpfsHashByAddress";

    public static final String FUNC_GETUSERCOUNT = "getUserCount";

    public static final String FUNC_GETADDRESSBYINDEX = "getAddressByIndex";

    public static final String FUNC_CREATEPURCHASEOFFER = "createPurchaseOffer";

    public static final String FUNC_GETOFFERBYINDEX = "getOfferByIndex";

    public static final String FUNC_CURRENCY = "currency";

    public static final String FUNC_ACCEPTPURCHASEOFFER = "acceptPurchaseOffer";

    public static final String FUNC_GETUSERNAMEBYADDRESS = "getUsernameByAddress";

    public static final String FUNC_GETUSERBYUSERNAME = "getUserByUsername";

    public static final String FUNC_GETUSERBYINDEX = "getUserByIndex";

    protected DynoMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DynoMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public RemoteCall<BigInteger> getOffersLength() {
        final Function function = new Function(FUNC_GETOFFERSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<TransactionReceipt> createPurchaseOffer(String seller, byte[] buyerPublicKey, BigInteger tokenAmount) {
        final Function function = new Function(
                FUNC_CREATEPURCHASEOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(seller), 
                new org.web3j.abi.datatypes.DynamicBytes(buyerPublicKey), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<String, String, byte[], BigInteger, byte[]>> getOfferByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETOFFERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple5<String, String, byte[], BigInteger, byte[]>>(
                new Callable<Tuple5<String, String, byte[], BigInteger, byte[]>>() {
                    @Override
                    public Tuple5<String, String, byte[], BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, byte[], BigInteger, byte[]>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<String> currency() {
        final Function function = new Function(FUNC_CURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> acceptPurchaseOffer(BigInteger index, byte[] ipfsHash) {
        final Function function = new Function(
                FUNC_ACCEPTPURCHASEOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index), 
                new org.web3j.abi.datatypes.DynamicBytes(ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public static RemoteCall<DynoMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(token)));
        return deployRemoteCall(DynoMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<DynoMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(token)));
        return deployRemoteCall(DynoMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static DynoMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DynoMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DynoMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DynoMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
