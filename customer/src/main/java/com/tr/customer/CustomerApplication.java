package com.tr.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 本项目包含功能:
 * 	1.nacos
 * 	2.openfeign
 * 	3.gateway（需启动 gateway 项目）
 * 	4.sentinel
 *
 * @Author: TR
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
