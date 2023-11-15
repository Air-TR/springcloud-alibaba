package com.tr.auth.service;

import com.tr.auth.controller.dto.UserRoleAddDto;
import com.tr.auth.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> save(UserRoleAddDto addDto);

}
