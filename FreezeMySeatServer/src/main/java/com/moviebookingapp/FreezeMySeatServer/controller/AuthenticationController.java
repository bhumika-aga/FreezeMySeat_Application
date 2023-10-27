package com.moviebookingapp.FreezeMySeatServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.FreezeMySeatServer.entity.User;
import com.moviebookingapp.FreezeMySeatServer.exception.UserNotFoundException;
import com.moviebookingapp.FreezeMySeatServer.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1.0/moviebooking/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@GetMapping("/AllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUsers() throws UserNotFoundException {
		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<>(users, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}