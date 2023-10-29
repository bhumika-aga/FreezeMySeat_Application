package com.moviebookingapp.MovieBookingService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.entity.User;
import com.moviebookingapp.MovieBookingService.exception.UserCreationException;
import com.moviebookingapp.MovieBookingService.exception.UserNotFoundException;
import com.moviebookingapp.MovieBookingService.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1.0/moviebooking/user")
public class UserController {
	private Map<String, String> map = new HashMap<String, String>();

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserCreationException {
		try {
			User newUser = userService.addUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (UserCreationException e) {
			return new ResponseEntity<>("Registration Failed!", HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password)
			throws UserNotFoundException {
		try {
			Authentication auth = null;
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			if (!auth.isAuthenticated()) {
				map.put("Message:", "User login failed!");
				map.put("Token:", null);
				return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
			}
			String role = userService.getUserRole(username, password);
			String token = jwtService.generateToken(username, role);
			map.put("Message", "User Successfully logged in!");
			map.put("Token:", token);
			return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User not found! Please check the username or password!", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getRole")
	public ResponseEntity<?> getRole(@RequestParam String username, @RequestParam String password)
			throws UserNotFoundException {
		try {
			return new ResponseEntity<>(userService.getUserRole(username, password), HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam String username, @RequestParam String password) {
		try {
			return new ResponseEntity<>(userService.getUser(username, password), HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<?> getByUsername(@PathVariable("username") String username) throws UserNotFoundException {
		try {
			return new ResponseEntity<>(userService.getUser(username), HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam(name = "username") String username,
			@RequestParam(name = "email") String email, @RequestParam(name = "newPassword") String password)
			throws UserNotFoundException {
		try {
			return new ResponseEntity<>(userService.forgotPassword(username, email, password), HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}