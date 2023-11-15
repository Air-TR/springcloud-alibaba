package com.tr.openfeign.controller;

import com.tr.openfeign.feignclient.CustomerFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@RestController
public class FeignController {

    @GetMapping("/call")
    public String call() {
        return "OpenFeign Success";
    }

    @Resource
    private CustomerFeignClient customerFeignClient;

    /**
     * 此处 Api 路径为当前 openfeign 系统 Api，与 feignCustomerPort() 配置的 Api 路径无任何关系，正好相同而已
     */
    @GetMapping("/customer/port")
    public String feignCustomerPort() {
        return customerFeignClient.feignCustomerPort();
    }

}
