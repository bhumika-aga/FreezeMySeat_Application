package com.moviebookingapp.MovieBookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.moviebookingapp.MovieBookingService.config.JwtFilter;

@SpringBootApplication
public class MovieBookingServiceApplication {

	static Logger logger = LoggerFactory.getLogger(MovieBookingServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingServiceApplication.class, args);
		logger.info("FreezeMySeatServer is running...");
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilterBean() {
		FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new JwtFilter());
		filter.addUrlPatterns("/api/v1.0/movibooking/**");
		return filter;
	}
}