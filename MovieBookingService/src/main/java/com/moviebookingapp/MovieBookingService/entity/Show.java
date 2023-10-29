package com.moviebookingapp.MovieBookingService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String showId;
	private String showName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate showDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime showStartTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime showEndTime;
	@OneToOne(mappedBy = "show")
	private Movie movie;
	@ManyToOne
	@JsonIgnore
	private Screen screen;
	@ManyToOne
	@JsonIgnore
	private Theatre theatre;
	@OneToOne
	@JsonIgnore
	private Ticket booking;

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Ticket getBooking() {
		return booking;
	}

	public void setBooking(Ticket booking) {
		this.booking = booking;
	}

	public String getShowId() {
		return showId;
	}

	@Override
	public String toString() {
		return "Show [showId=" + showId + ", showName=" + showName + ", showDate=" + showDate + ", showStartTime="
				+ showStartTime + ", showEndTime=" + showEndTime + ", movie=" + movie + ", screen=" + screen
				+ ", theatre=" + theatre + ", booking=" + booking + "]";
	}

	public Show() {
	}

	public Show(String showName, LocalDate showDate, LocalDateTime showStartTime, LocalDateTime showEndTime,
			Movie movie, Screen screen, Theatre theatre, Ticket booking) {
		this.showName = showName;
		this.showDate = showDate;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.movie = movie;
		this.screen = screen;
		this.theatre = theatre;
		this.booking = booking;
	}
}