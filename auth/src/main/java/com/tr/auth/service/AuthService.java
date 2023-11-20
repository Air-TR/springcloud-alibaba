package com.tr.auth.service;

import com.tr.auth.entity.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface AuthService {

    User register(User user);

    OAuth2AccessToken login(User user);

    Boolean logout();

}
