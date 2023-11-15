package com.tr.auth.service.impl;

import com.tr.auth.controller.dto.UserUpdateDto;
import com.tr.auth.entity.User;
import com.tr.auth.repository.UserRepository;
import com.tr.auth.service.UserService;
import com.tr.auth.exception.BusinessException;
import com.tr.auth.kit.BeanKit;
import com.tr.auth.result.ResultEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(ResultEnum.USER_NOT_EXIST));
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(ResultEnum.USER_NOT_EXIST));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    public User update(UserUpdateDto updateDto) {
        User user = findById(updateDto.getId());
        BeanKit.copyPropertiesIgnoreNull(updateDto, user);
        return userRepository.save(user);
    }

}
