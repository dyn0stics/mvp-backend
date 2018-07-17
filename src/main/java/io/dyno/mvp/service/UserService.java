package io.dyno.mvp.service;

import io.dyno.mvp.model.UserProfile;

public interface UserService {

    UserProfile registerUser(final String username) throws Exception;

    UserProfile loginUser(final String privateKey) throws Exception;
}
