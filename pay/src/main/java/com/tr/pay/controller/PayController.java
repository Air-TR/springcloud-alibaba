package com.tr.pay.controller;

import cn.hutool.json.JSONObject;
import com.tr.pay.kit.JwtKit;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 */
@Api(tags = "Pay")
@RestController
public class PayController {

    @GetMapping("/pay")
    public String pay() {
        return "pay success";
    }

    @GetMapping("/ex-path")
    public String exPath() {
        return "ex-path success";
    }

    @GetMapping("/authInfo")
    public JSONObject authInfo() {
        System.out.println(JwtKit.getUserId());
        System.out.println(JwtKit.getUsername());
        System.out.println(JwtKit.getAuthorities());
        return JwtKit.getClaimsJson();
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        // auth client 中 authentication 是 OAuth2AccessToken，auth server 中是 UsernamePasswordAuthenticationToken
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());
        return authentication;
    }

}
