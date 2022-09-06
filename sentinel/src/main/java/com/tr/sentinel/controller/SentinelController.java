package com.tr.sentinel.controller;

import com.tr.sentinel.service.SentinelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TR
 * @date 2022/8/18 上午9:44
 */
@RestController
public class SentinelController {

    @Value("${server.port}")
    private String port;

    @Resource
    SentinelService sentinelService;

    @GetMapping("/call")
    public String call() {
        return "Sentinel Success";
    }

    @GetMapping("/port")
    public String getPort() {
        return "sentinel: " + port + " --> " + new Date();
    }

    @GetMapping("/hello")
    public String hello() {
        return sentinelService.sayHello();
    }

}
