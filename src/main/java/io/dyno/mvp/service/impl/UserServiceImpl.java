package io.dyno.mvp.service.impl;

import io.dyno.mvp.controller.SystemController;
import io.dyno.mvp.model.UserProfile;
import io.dyno.mvp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    Web3j web3j;

    @Override
    public UserProfile registerUser(String username) throws Exception {
        final UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        userProfile.setIpfsHash("not-available");

        final String seed = UUID.randomUUID().toString();
        final ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        final BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
        final String sPrivatekeyInHex = privateKeyInDec.toString(16);
        final WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
        final String sAddress = aWallet.getAddress();

        userProfile.setPrivateKey(sPrivatekeyInHex);
        userProfile.setAddress(sAddress);
        return userProfile;
    }

}
