package io.dyno.mvp.service;

import io.dyno.mvp.model.PurchaseOffer;
import io.dyno.mvp.model.UserProfile;

import java.util.List;

public interface UserService {

    UserProfile getUser(final String privateKey) throws Exception;

    List<UserProfile> doSearch(final String param) throws Exception;

    UserProfile registerUser(String username, String age, String weight, String gender, String country) throws Exception;

    String purchase(final String address, final String price, final String pk) throws Exception;

    List<PurchaseOffer> getOffersForBuyer(final String pk) throws Exception;

}
