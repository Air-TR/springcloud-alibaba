package com.tr.auth.service;

import com.tr.auth.controller.dto.RoleUpdateDto;
import com.tr.auth.entity.Role;

public interface RoleService {

    Role save(Role role);

    Role findById(Integer id);

    void deleteById(Integer id);

    Role update(RoleUpdateDto updateDto);

}
