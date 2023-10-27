package com.moviebookingapp.MovieBookingService.controller;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.moviebookingapp.MovieBookingService.entity.User;

@RestController
@CrossOrigin("*")
@RequestMapping("/call/consumer")
public class ConsumerController {

	@PostMapping("/login")
	public ResponseEntity<?> login(User user) throws RestClientException, Exception {
		String baseUrl = "http://localhost:8081/api/v1.0/moviebooking/user/login";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> result;
		try {
			result = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(user),
					new ParameterizedTypeReference<Map<String, String>>() {
					});
			return new ResponseEntity<>(result.getBody(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Login failed!", HttpStatus.UNAUTHORIZED);
		}
	}

	private HttpEntity<?> getHeaders(User user) {
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(user, header);
	}
}