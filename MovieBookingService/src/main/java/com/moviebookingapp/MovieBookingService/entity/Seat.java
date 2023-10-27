package com.moviebookingapp.MovieBookingService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String seatId;
	private String seatNumber;
	@Enumerated(EnumType.STRING)
	private SeatStatus status;
	@ManyToOne
	@JsonIgnore
	private Ticket ticket;

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public Ticket getBooking() {
		return ticket;
	}

	public void setBooking(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getSeatId() {
		return seatId;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNumber=" + seatNumber + ", status=" + status + ", ticket=" + ticket
				+ "]";
	}

	public Seat() {
	}

	public Seat(String seatNumber, SeatStatus status, Ticket ticket) {
		this.seatNumber = seatNumber;
		this.status = status;
		this.ticket = ticket;
	}
}