package com.tr.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 只需要引入 spring-cloud-starter-alibaba-sentinel 就能在 Sentinel Dashboard 监控显示（还有配置文件中 spring.cloud.sentinel 配置）
 * 启动完成，需要调用一下任一接口，才会在 Sentinel Dashboard 显示该项目
 *
 * 注：Sentinel Dashboard 默认不支持 nacos 的持久化，需要修改源码
 *
 * @author TR
 * @date 2022/8/18 上午10:11
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApplication.class, args);
	}

}
