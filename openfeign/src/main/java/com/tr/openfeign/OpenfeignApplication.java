package com.tr.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 此项目：验证 Openfeign 相关功能
 *
 * @author TR
 * @date 2022/8/18 上午10:37
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OpenfeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenfeignApplication.class, args);
	}

}
