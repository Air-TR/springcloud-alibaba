package com.tr.order.controller;

import com.alibaba.fastjson.JSON;
import com.tr.order.entity.Order;
import com.tr.order.jpa.OrderJpa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author TR
 * @date 2021/11/29 下午7:41
 */
@RestController
@RefreshScope /** 支持 Nacos 的动态刷新功能 */
public class OrderController {

    @GetMapping("/feign/test")
    public String orderFeignTest() {
//        int a = 1 / 0;
        return "Order Feign Success，from: " + port;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return port;
    }

    @Resource
    private OrderJpa orderJpa;

    @GetMapping("/orders/findByCustomerId/{customerId}")
    public List<Order> findByCustomerId(@PathVariable Integer customerId) {
        return orderJpa.findByCustomerId(customerId);
    }

    @PostMapping("/order/save")
    public Order save(@RequestBody Order order) {
        return orderJpa.save(order);
    }

    @GetMapping("/orders/{id}")
    public Order findById(@PathVariable Integer id) {
        Optional<Order> optional = orderJpa.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @GetMapping("/orders/all")
    public List<Order> findAll() {
        return orderJpa.findAll();
    }

    @GetMapping("/feign/orders/all")
    public String feignFindAll() {
        return JSON.toJSONString(orderJpa.findAll());
    }

}
