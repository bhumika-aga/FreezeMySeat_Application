package com.moviebookingapp.MovieBookingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.exception.UserNotFoundException;
import com.moviebookingapp.MovieBookingService.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/AllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUsers() throws UserNotFoundException {
		try {
			return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/generateToken")
	public String generateToken(@RequestParam String username, @RequestParam String password) {
		return jwtService.generateToken(username);
	}
}