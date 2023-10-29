package com.moviebookingapp.MovieBookingService.exception;

public class ShowNotFoundException extends Exception {

	public ShowNotFoundException() {
	}

	public ShowNotFoundException(String message) {
		super(message);
	}
}