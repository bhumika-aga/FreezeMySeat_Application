package com.moviebookingapp.MovieBookingService.exception;

public class TicketNotFoundException extends Exception {

	private static final long serialVersionUUID = 1L;

	public TicketNotFoundException() {
	}

	public TicketNotFoundException(String message) {
		super(message);
	}
}