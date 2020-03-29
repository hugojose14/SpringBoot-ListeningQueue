package com.bolsaideas.springboot.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ApiHugoListeningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHugoListeningApplication.class, args);
	}

}
