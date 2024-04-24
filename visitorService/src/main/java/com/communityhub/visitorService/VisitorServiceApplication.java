package com.communityhub.visitorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.communityhub.visitorService.repository")
public class VisitorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorServiceApplication.class, args);
	}

}
