package com.estock.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.estock.user.filter.JwtFilter;

@SpringBootApplication
//@EnableEurekaClient
public class UserManagementApplication {

	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		FilterRegistrationBean fb = new FilterRegistrationBean<>();
		fb.setFilter(new JwtFilter());
		fb.addUrlPatterns("/api/v1/*");
		return fb;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
