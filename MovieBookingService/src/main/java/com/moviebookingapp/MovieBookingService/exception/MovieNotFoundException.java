package com.moviebookingapp.MovieBookingService.exception;

public class MovieNotFoundException extends Exception {

	public MovieNotFoundException() {
	}

	public MovieNotFoundException(String message) {
		super(message);
	}
}