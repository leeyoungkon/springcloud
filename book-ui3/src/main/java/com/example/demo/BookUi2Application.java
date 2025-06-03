package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.client")
public class BookUi2Application {

	public static void main(String[] args) {
		SpringApplication.run(BookUi2Application.class, args);
	}

}
