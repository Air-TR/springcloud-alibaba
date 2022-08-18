package com.tr.openfeign.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author TR
 * @date 2022/8/18 上午10:41
 */
@FeignClient(name = "customer") // feign 到注册名为 "customer" 的服务
public interface CustomerFeignClient {

    @GetMapping("/customer/port") // 该路径为需要调用的 customer 系统的 Api 路径
    String feignCustomerPort();

}
