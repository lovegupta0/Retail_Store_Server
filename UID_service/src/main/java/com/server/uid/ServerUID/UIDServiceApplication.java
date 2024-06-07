package com.server.uid.ServerUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class UIDServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UIDServiceApplication.class, args);
	}

}
