package com.moviebookingapp.MovieBookingService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Ticket;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TicketNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.UserNotFoundException;

@Service
public interface TicketService {

	public Ticket addTicket(Ticket ticket, String showId) throws ShowNotFoundException, Exception;

	public List<Ticket> viewTicketList() throws TicketNotFoundException;

	public List<Ticket> viewAllTicketsOfCustomer(String username) throws TicketNotFoundException, UserNotFoundException;

	public Ticket getTicketById(String ticketId) throws TicketNotFoundException;

	public boolean cancelTicket(String ticketId) throws TicketNotFoundException;
}