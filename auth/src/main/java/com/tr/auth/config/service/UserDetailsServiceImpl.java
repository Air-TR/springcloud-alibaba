package com.tr.auth.config.service;

import com.tr.auth.entity.Role;
import com.tr.auth.entity.User;
import com.tr.auth.repository.RoleRepository;
import com.tr.auth.service.impl.UserServiceImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: TR
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户
        User user = userService.findByUsername(username);
        // 获取用户权限（注：权限一定要以 "ROLE_" 开头，如果数据库中存储的 role_name 没有 "ROLE_" 前缀，则在这里加上，如果有，则不作处理）
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_".concat(role.getRolename()))).collect(toList());
        // 封装成 UserDetails 对象返回
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
