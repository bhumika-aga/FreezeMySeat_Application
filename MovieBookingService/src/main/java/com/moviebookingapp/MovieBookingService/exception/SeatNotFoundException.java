package com.moviebookingapp.MovieBookingService.exception;

public class SeatNotFoundException extends Exception {

	private static final long serialVersionUUID = 1L;

	public SeatNotFoundException() {
	}

	public SeatNotFoundException(String message) {
		super(message);
	}
}