package com.tr.auth.controller;

import com.tr.auth.controller.dto.LoginDto;
import com.tr.auth.controller.dto.RegisterDto;
import com.tr.auth.entity.User;
import com.tr.auth.kit.ConvertKit;
import com.tr.auth.kit.TokenKit;
import com.tr.auth.service.RegisterService;
import com.tr.auth.service.impl.LoginAndOutServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: TR
 */
@Api(tags = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private RegisterService registerService;

    @Resource
    private LoginAndOutServiceImpl loginAndOutService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterDto registerDto) {
        return registerService.register(ConvertKit.convert(registerDto, User.class));
    }

    /**
     * 登录成功返回 token
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        return loginAndOutService.login(ConvertKit.convert(loginDto, User.class));
    }

    @ApiOperation(value = "注销登录")
    @PostMapping("/logout")
    public Boolean logout(HttpServletRequest request) {
        return loginAndOutService.logout(TokenKit.getToken(request));
    }

}
