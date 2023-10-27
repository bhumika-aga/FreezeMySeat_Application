package com.moviebookingapp.MovieBookingService.exception;

public class MovieNotFoundException extends Exception {

	private static final long serialVersionUUID = 1L;

	public MovieNotFoundException() {
	}

	public MovieNotFoundException(String message) {
		super(message);
	}
}