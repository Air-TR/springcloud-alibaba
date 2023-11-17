package com.tr.auth.service.impl;

import com.tr.auth.controller.dto.RoleUpdateDto;
import com.tr.auth.entity.Role;
import com.tr.auth.common.kit.BeanKit;
import com.tr.auth.common.kit.StringKit;
import com.tr.auth.repository.RoleRepository;
import com.tr.auth.service.RoleService;
import com.tr.auth.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: TR
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        if (Objects.nonNull(roleRepository.findByRolename(role.getRolename()))) {
            throw new BusinessException("角色" + role.getRolename() + "已存在");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new BusinessException("not found by id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(RoleUpdateDto updateDto) {
        if (StringKit.isNotBlank(updateDto.getRolename())) {
            if (Objects.nonNull(roleRepository.findByRolename(updateDto.getRolename()))) {
                throw new BusinessException("角色" + updateDto.getRolename() + "已存在");
            }
        }
        Role role = findById(updateDto.getId());
        BeanKit.copyPropertiesIgnoreNull(updateDto, role);
        return roleRepository.save(role);
    }

}
