package com.tr.auth.service;

import com.tr.auth.controller.dto.UserUpdateDto;
import com.tr.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User findById(Integer id);

    void deleteById(Integer id);

    User update(UserUpdateDto updateDto);

}
