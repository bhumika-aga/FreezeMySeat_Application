package com.moviebookingapp.MovieBookingService.service;

import java.util.List;

import com.moviebookingapp.MovieBookingService.entity.Seat;
import com.moviebookingapp.MovieBookingService.exception.SeatNotFoundException;

public interface SeatService {

	public Seat addSeat(Seat seat) throws SeatNotFoundException;

	public List<Seat> viewAllSeats();

	public Seat blockSeat(Seat seat) throws SeatNotFoundException;

	public Seat bookSeat(Seat seat) throws SeatNotFoundException;

	public Seat updateSeat(Seat seat) throws SeatNotFoundException;

	public Seat cancelSeatBooking(Seat seat) throws SeatNotFoundException;
}