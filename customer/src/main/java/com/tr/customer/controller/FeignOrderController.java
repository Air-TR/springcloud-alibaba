package com.tr.customer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tr.customer.feign.OrderFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TR
 * @date 2021/12/7 下午4:57
 */
@RestController
public class FeignOrderController {

    @Resource
    private OrderFeign orderFeign;

    @GetMapping("/feign/order")
    @SentinelResource(fallback = "handlerFallback")
    public String feignOrderTest() {
        return orderFeign.feignOrderTest();
    }

    public String handlerFallback() {
        return "Sentinel Fallback";
    }

    @GetMapping("/feign/orders/all")
    public String feignOrdersAll() {
        return orderFeign.feignOrdersAll();
    }

}
