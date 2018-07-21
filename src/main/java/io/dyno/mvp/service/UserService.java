package io.dyno.mvp.service;

import io.dyno.mvp.model.UserProfile;

import java.util.List;

public interface UserService {

    UserProfile registerUser(final String username) throws Exception;

    UserProfile getUser(final String privateKey) throws Exception;

    List<UserProfile> doSearch(final String param) throws Exception;
}
