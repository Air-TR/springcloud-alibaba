package com.tr.sentinel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TR
 * @date 2022/8/18 上午9:44
 */
@RestController
public class SentinelController {

    @Value("${server.port}")
    private String port;

//    @Resource
//    SentinelService sentinelService;

    @GetMapping("/port")
    public String getPort() {
        return port;
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return sentinelService.sayHello();
//    }

}
