package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;


@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerExampleApplication.class, args);
	}

}
