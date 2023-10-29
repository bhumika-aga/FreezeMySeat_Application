package com.moviebookingapp.MovieBookingService.service;

public interface InputValidatorService {

	public boolean usernameValidator(String username);

	public boolean passwordValidator(String password);
}