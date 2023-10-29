package com.moviebookingapp.MovieBookingService.exception;

public class SeatNotFoundException extends Exception {

	public SeatNotFoundException() {
	}

	public SeatNotFoundException(String message) {
		super(message);
	}
}