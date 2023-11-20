package com.tr.auth.controller;

import cn.hutool.json.JSONObject;
import com.tr.auth.common.kit.ConvertKit;
import com.tr.auth.common.kit.JwtKit;
import com.tr.auth.controller.dto.LoginDto;
import com.tr.auth.controller.dto.RegisterDto;
import com.tr.auth.entity.User;
import com.tr.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    /**
     * /logout 被 security 占用
     *  直接使用 /logout 会返回 Request method 'XXX(POST/GET/PUT)' not supported
     */
    @ApiOperation(value = "注销登录")
    @PostMapping("/logout1") //
    public Boolean logout() {
        return authService.logout();
    }

    @GetMapping("/authInfo")
    public JSONObject authInfo() {
        System.out.println(JwtKit.getUserId());
        System.out.println(JwtKit.getUsername());
        System.out.println(JwtKit.getAuthorities());
        return JwtKit.getClaimsJson();
    }

    /**
     * 同样的接口和方法，这里打印的结果和 client 中不同，如 pay 中 /pay/authentication
     *  auth server 中 authentication 是 UsernamePasswordAuthenticationToken，auth client 中是 OAuth2AccessToken
     */
    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());
        return authentication;
    }

}
