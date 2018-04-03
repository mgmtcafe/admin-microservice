package com.ctscafe.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableResourceServer
@EnableJpaRepositories("com.ctscafe.admin.repository")
@CrossOrigin
public class AdminApp {

	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);

	}
}
