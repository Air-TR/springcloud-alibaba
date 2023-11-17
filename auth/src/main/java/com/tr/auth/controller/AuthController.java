package com.tr.auth.controller;

import com.tr.auth.controller.dto.LoginDto;
import com.tr.auth.controller.dto.RegisterDto;
import com.tr.auth.entity.User;
import com.tr.auth.common.kit.ConvertKit;
import com.tr.auth.common.kit.TokenKit;
import com.tr.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: TR
 */
@Api(tags = "Auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterDto registerDto) {
        return authService.register(ConvertKit.convert(registerDto, User.class));
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public OAuth2AccessToken login(@RequestBody @Valid LoginDto loginDto) {
        return authService.login(ConvertKit.convert(loginDto, User.class));
    }

    @ApiOperation(value = "注销登录")
    @PostMapping("/logout")
    public Boolean logout(HttpServletRequest request) {
        return authService.logout(TokenKit.getToken(request));
    }

}
