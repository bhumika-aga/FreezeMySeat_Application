package com.moviebookingapp.MovieBookingService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Seat;
import com.moviebookingapp.MovieBookingService.entity.SeatStatus;
import com.moviebookingapp.MovieBookingService.exception.SeatNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.SeatRepository;
import com.moviebookingapp.MovieBookingService.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;

	@Override
	public Seat addSeat(Seat seat) throws SeatNotFoundException {
		if (seat.equals(null) || !seatRepository.findById(seat.getSeatId()).get().equals(null)) {
			throw new SeatNotFoundException("Seat could not be added!");
		}
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public List<Seat> viewAllSeats() {
		return seatRepository.findAll();
	}

	@Override
	public Seat blockSeat(Seat seat) throws SeatNotFoundException {
		seat.setStatus(SeatStatus.BLOCKED);
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat bookSeat(Seat seat) throws SeatNotFoundException {
		seat.setStatus(SeatStatus.BOOKED);
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat cancelSeatBooking(Seat seat) throws SeatNotFoundException {
		seat.setStatus(SeatStatus.AVAILABLE);
		return seatRepository.saveAndFlush(seat);
	}

	@Override
	public Seat updateSeat(Seat seat) throws SeatNotFoundException {
		if (seat.equals(null) || seatRepository.findById(seat.getSeatId()).get().equals(null)) {
			throw new SeatNotFoundException("Seat does not exist!");
		}
		return seatRepository.saveAndFlush(seat);
	}
}