package com.tr.sentinelpushnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelPushNacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelPushNacosApplication.class, args);
	}

}
