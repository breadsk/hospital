package com.microservice.atencion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAtencionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAtencionApplication.class, args);
	}

}
