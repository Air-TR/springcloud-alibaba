package com.tr.auth.service.impl;

import com.tr.auth.controller.dto.UserRoleAddDto;
import com.tr.auth.entity.UserRole;
import com.tr.auth.repository.UserRoleRepository;
import com.tr.auth.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TR
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> save(UserRoleAddDto addDto) {
        List<UserRole> userRoleList = new ArrayList<>();
        addDto.getRoleIds().forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(addDto.getUserId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        });
        return userRoleRepository.saveAll(userRoleList);
    }

}
