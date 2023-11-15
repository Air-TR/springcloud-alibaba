package com.tr.auth.controller;

import com.tr.auth.controller.dto.RoleAddDto;
import com.tr.auth.controller.dto.RoleUpdateDto;
import com.tr.auth.controller.dto.UserRoleAddDto;
import com.tr.auth.entity.Role;
import com.tr.auth.entity.UserRole;
import com.tr.auth.kit.ConvertKit;
import com.tr.auth.service.RoleService;
import com.tr.auth.service.UserRoleService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: TR
 */
@Api(tags = "Role")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/role/add")
    public Role add(@RequestBody @Valid RoleAddDto addDto) {
        return roleService.save(ConvertKit.convert(addDto, Role.class));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/role")
    public Role update(@RequestBody @Valid RoleUpdateDto updateDto) {
        return roleService.update(updateDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/role/{id}")
    public void deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user-role/add")
    public List<UserRole> add(@RequestBody @Valid UserRoleAddDto addDto) {
        return userRoleService.save(addDto);
    }

}
