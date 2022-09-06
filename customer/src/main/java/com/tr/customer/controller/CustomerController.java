package com.tr.customer.controller;

import com.tr.customer.entity.Customer;
import com.tr.customer.jpa.CustomerJpa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author TR
 * @date 2021/12/2 下午7:06
 */
@RestController
@RefreshScope /** 支持 Nacos 的动态刷新功能 */
public class CustomerController {

    @Value("${config.value}")
    private String configValue;

    @GetMapping("/config/value")
    public String getValue() {
        return configValue;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return "customer: " + port + " --> " + new Date();
    }

    @Resource
    private CustomerJpa customerJpa;

    @GetMapping("/customer/{id}")
    public Customer getById(@PathVariable Integer id) {
        Optional<Customer> optional = customerJpa.findById(id);
        return optional.isPresent() ? optional.get(): null;
    }

    @GetMapping("/customers/all")
    public List<Customer> getAll() {
        return customerJpa.findAll();
    }

    @PostMapping("/customer/save")
    public Customer save(@RequestBody Customer customer) {
        return customerJpa.save(customer);
    }

}
