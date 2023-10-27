package com.moviebookingapp.MovieBookingService.exception;

public class TheatreNotFoundException extends Exception {

	private static final long serialVersionUUID = 1L;

	public TheatreNotFoundException(String message) {
		super(message);
	}
}