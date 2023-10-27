package com.moviebookingapp.FreezeMySeatServer.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}