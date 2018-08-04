package io.dyno.mvp.model;

import java.util.List;

public class UserProfile {

    private String username;
    private String privateKey;
    private String ipfsHash;
    private String address;
    private UserData data;
    private String balance;
    private List<PurchaseOffer> purchaseOffers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getIpfsHash() {
        return ipfsHash;
    }

    public void setIpfsHash(String ipfsHash) {
        this.ipfsHash = ipfsHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<PurchaseOffer> getPurchaseOffers() {
        return purchaseOffers;
    }

    public void setPurchaseOffers(List<PurchaseOffer> purchaseOffers) {
        this.purchaseOffers = purchaseOffers;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", ipfsHash='" + ipfsHash + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
