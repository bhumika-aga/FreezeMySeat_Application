package com.moviebookingapp.MovieBookingService.service.impl;

import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.service.InputValidatorService;

@Service
public class InputValidatorServiceImpl implements InputValidatorService {

	@Override
	public boolean usernameValidator(String username) {
		return username.matches("[A-Za-z]{3,20}$");
	}

	@Override
	public boolean passwordValidator(String password) {
		return password.length() >= 3;
	}
}