package com.moviebookingapp.MovieBookingService.service;

import org.springframework.stereotype.Service;

@Service
public interface InputValidatorService {

	public boolean usernameValidator(String username);

	public boolean passwordValidator(String password);
}