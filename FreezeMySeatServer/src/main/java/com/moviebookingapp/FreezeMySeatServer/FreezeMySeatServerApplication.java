package com.moviebookingapp.FreezeMySeatServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.moviebookingapp.FreezeMySeatServer.component.JwtFilter;

@SpringBootApplication
public class FreezeMySeatServerApplication {

	static Logger logger = LoggerFactory.getLogger(FreezeMySeatServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FreezeMySeatServerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilterBean() {
		FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new JwtFilter());
		filter.addUrlPatterns("/api/v1.0/moviebooking/**");
		return filter;
	}
}