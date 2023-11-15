package com.tr.auth.service;

import com.tr.auth.controller.dto.UserUpdateDto;
import com.tr.auth.entity.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Integer id);

    void deleteById(Integer id);

    User update(UserUpdateDto updateDto);

}
