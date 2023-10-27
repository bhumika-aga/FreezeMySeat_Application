package com.moviebookingapp.MovieBookingService.exception;

public class ShowNotFoundException extends Exception {

	private static final long serialVersionuUUID = 1L;

	public ShowNotFoundException() {
	}

	public ShowNotFoundException(String message) {
		super(message);
	}
}