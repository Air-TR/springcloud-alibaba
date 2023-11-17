package com.tr.pay.controller;

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

    @GetMapping("/ex")
    public String ex() {
        return "ex success";
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        return authentication;
    }

}
