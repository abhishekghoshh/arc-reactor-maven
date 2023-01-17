package com.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReddisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReddisApplication.class, args);
	}

}
