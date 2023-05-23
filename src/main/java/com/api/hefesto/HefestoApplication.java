package com.api.hefesto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HefestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HefestoApplication.class, args);
	}

}
