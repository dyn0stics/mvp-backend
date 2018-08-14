package io.dyno.mvp.model;

import java.math.BigInteger;

public class PurchaseOffer {

    private String buyer;
    private String seller;
    private String buyerPublicKey;
    private BigInteger amount;
    private String ipfs;

    public PurchaseOffer(String buyer, String seller, String buyerPublicKey, BigInteger amount, String ipfs) {
        this.buyer = buyer;
        this.seller = seller;
        this.buyerPublicKey = buyerPublicKey;
        this.amount = amount;
        this.ipfs = ipfs;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyerPublicKey() {
        return buyerPublicKey;
    }

    public void setBuyerPublicKey(String buyerPublicKey) {
        this.buyerPublicKey = buyerPublicKey;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getIpfs() {
        return ipfs;
    }

    public void setIpfs(String ipfs) {
        this.ipfs = ipfs;
    }
}
