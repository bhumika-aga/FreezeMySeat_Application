package com.moviebookingapp.MovieBookingService.entity;

import java.util.List;

import jakarta.persistence.OneToMany;

public class User {

	private String username;
	private String password;
	@OneToMany
	private List<Ticket> bookings;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Ticket> getBookings() {
		return bookings;
	}

	public void setBookings(List<Ticket> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", bookings=" + bookings + "]";
	}
}