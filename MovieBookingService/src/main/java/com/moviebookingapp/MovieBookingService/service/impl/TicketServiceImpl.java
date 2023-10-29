package com.moviebookingapp.MovieBookingService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Seat;
import com.moviebookingapp.MovieBookingService.entity.SeatStatus;
import com.moviebookingapp.MovieBookingService.entity.Ticket;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TicketNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.UserNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.ShowRepository;
import com.moviebookingapp.MovieBookingService.repository.TicketRepository;
import com.moviebookingapp.MovieBookingService.service.InputValidatorService;
import com.moviebookingapp.MovieBookingService.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private InputValidatorService validator;

	@Override
	public Ticket addTicket(Ticket ticket, String showId) throws ShowNotFoundException, Exception {
		if (ticket.equals(null) || ticketRepository.findById(ticket.getTicketId()).isEmpty()) {
			throw new Exception("Ticket cannot be created!");
		} else if (showId.equals(null) || showRepository.findById(showId).get().equals(null)) {
			throw new ShowNotFoundException("Show does not exist!");
		}
		showRepository.findById(showId).get().setBooking(ticket);
		ticket.setShow(showRepository.findById(showId).get());
		showRepository.saveAndFlush(showRepository.findById(showId).get());
		return ticketRepository.saveAndFlush(ticket);
	}

	@Override
	public List<Ticket> viewTicketList() throws TicketNotFoundException {
		if (ticketRepository.findAll().isEmpty()) {
			throw new TicketNotFoundException("Ticket Does not exist!");
		}
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getTicketById(String ticketId) throws TicketNotFoundException {
		if (ticketId.equals(null) || ticketRepository.findById(ticketId).isEmpty()) {
			throw new TicketNotFoundException("Ticket does not exist");
		}
		return ticketRepository.findById(ticketId).get();
	}

	@Override
	public List<Ticket> viewAllTicketsOfCustomer(String username)
			throws TicketNotFoundException, UserNotFoundException {
		if (!validator.usernameValidator(username)) {
			throw new UserNotFoundException("User invalid!");
		}
		return ticketRepository.getTicketByUser(username);
	}

	@Override
	public boolean cancelTicket(String ticketId) throws TicketNotFoundException {
		if (ticketId.equals(null) || ticketRepository.findById(ticketId).isEmpty()) {
			throw new TicketNotFoundException("Ticket does not exist");
		}
		for (Seat seat : ticketRepository.findById(ticketId).get().getSeatsBooked()) {
			seat.setStatus(SeatStatus.AVAILABLE);
		}
		ticketRepository.deleteById(ticketId);
		return true;
	}
}