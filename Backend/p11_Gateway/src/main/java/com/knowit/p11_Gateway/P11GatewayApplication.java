package com.knowit.p11_Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class P11GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(P11GatewayApplication.class, args);
	}

}
