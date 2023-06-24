package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.mysql.cj.protocol.AuthenticationProvider;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		
		
		
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
		
	}
	
	@Bean
	PasswordEncoder bcryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	 

}
