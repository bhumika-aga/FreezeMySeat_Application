package com.moviebookingapp.MovieBookingService.entity;

public enum SeatStatus {
	AVAILABLE("Available"), BLOCKED("Blocked"), BOOKED("Booked");

	private String status;

	private SeatStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}