package com.moviebookingapp.MovieBookingService.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String ticketId;
	private long noOfSeats;
	private boolean ticketStatus;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate bookingdate;
	@OneToMany
	private List<Seat> seatsBooked;
	@JsonIgnore
	@OneToMany
	private List<Theatre> theatre;
	@JsonIgnore
	@ManyToMany
	private List<Movie> movie;
	@JsonIgnore
	@OneToOne(mappedBy = "booking")
	private Show show;
	@JsonIgnore
	@ManyToOne
	private User user;

	public long getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(long noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public boolean isTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public LocalDate getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(LocalDate bookingdate) {
		this.bookingdate = bookingdate;
	}

	public List<Seat> getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(List<Seat> seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public List<Theatre> getTheatre() {
		return theatre;
	}

	public void setTheatre(List<Theatre> theatre) {
		this.theatre = theatre;
	}

	public List<Movie> getMovie() {
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTicketId() {
		return ticketId;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", noOfSeats=" + noOfSeats + ", ticketStatus=" + ticketStatus
				+ ", bookingdate=" + bookingdate + ", seatsBooked=" + seatsBooked + ", theatre=" + theatre + ", movie="
				+ movie + ", show=" + show + ", user=" + user + "]";
	}

	public Ticket() {
	}

	public Ticket(long noOfSeats, boolean ticketStatus, LocalDate bookingdate, List<Seat> seatsBooked,
			List<Theatre> theatre, List<Movie> movie, Show show, User user) {
		this.noOfSeats = noOfSeats;
		this.ticketStatus = ticketStatus;
		this.bookingdate = bookingdate;
		this.seatsBooked = seatsBooked;
		this.theatre = theatre;
		this.movie = movie;
		this.show = show;
		this.user = user;
	}
}