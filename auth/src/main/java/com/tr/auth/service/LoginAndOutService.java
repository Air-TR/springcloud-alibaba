package com.tr.auth.service;

import com.tr.auth.entity.User;

public interface LoginAndOutService {

    String login(User user);

    Boolean logout(String token);

}
