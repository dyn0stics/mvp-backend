package io.dyno.mvp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserProfile {

    private String username;
    private String privateKey;
    private String ipfsHash;
    private String address;
    private UserData data;

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
