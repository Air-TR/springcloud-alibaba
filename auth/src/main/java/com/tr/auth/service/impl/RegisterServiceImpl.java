package com.tr.auth.service.impl;

import com.tr.auth.entity.User;
import com.tr.auth.kit.PasswordEncoderKit;
import com.tr.auth.repository.UserRepository;
import com.tr.auth.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        user.setPassword(PasswordEncoderKit.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
