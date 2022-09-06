package com.tr.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order") // feign 到注册名为 “order” 的服务
public interface OrderFeignClient {

    @GetMapping("/order/feign/test")
    String feignOrderTest();

    @GetMapping("/order/feign/orders/all")
    String feignOrdersAll();

}
