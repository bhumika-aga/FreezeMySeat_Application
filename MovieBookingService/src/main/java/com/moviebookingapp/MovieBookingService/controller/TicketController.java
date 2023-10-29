package com.moviebookingapp.MovieBookingService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.entity.Ticket;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TicketNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.UserNotFoundException;
import com.moviebookingapp.MovieBookingService.service.TicketService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/ticket")
public class TicketController {

	Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private KafkaMessageProducer producer;

	@PostMapping(value = "/createTicket", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTicket(@RequestBody Ticket ticket, @RequestParam(required = false) String customerId,
			@RequestParam(required = false) String showId, @RequestHeader("Authorization") String auth)
			throws ShowNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Ticket Added!");
			logger.info("New Ticket Created!");
			return new ResponseEntity<>(ticketService.addTicket(ticket, showId), HttpStatus.CREATED);
		} catch (ShowNotFoundException e) {
			logger.error("Show Not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewTickets")
	public ResponseEntity<?> viewTicketList() throws TicketNotFoundException {
		try {
			logger.info("Ticket List!");
			return new ResponseEntity<>(ticketService.viewTicketList(), HttpStatus.FOUND);
		} catch (TicketNotFoundException e) {
			logger.error("Ticket Does Not Exist!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewTicket/{ticketId}")
	public ResponseEntity<?> viewTicketById(@PathVariable("ticketId") String ticketId) throws TicketNotFoundException {
		try {
			logger.info("Ticket Found!");
			return new ResponseEntity<>(ticketService.getTicketById(ticketId), HttpStatus.FOUND);
		} catch (TicketNotFoundException e) {
			logger.error("Ticket Does Not Exist!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewTicketByCustomer/{username}")
	public ResponseEntity<?> viewTicketByCustomer(@PathVariable("username") String username)
			throws TicketNotFoundException, UserNotFoundException {
		try {
			logger.info("Ticket List!");
			return new ResponseEntity<>(ticketService.viewAllTicketsOfCustomer(username), HttpStatus.FOUND);
		} catch (TicketNotFoundException e) {
			logger.error("Ticket Does Not Exist!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (UserNotFoundException e) {
			logger.error("User Does Not Exist!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cancelTicket")
	public ResponseEntity<?> cancelTicket(String ticketId) throws TicketNotFoundException {
		try {
			logger.info("Ticket Deleted!");
			return new ResponseEntity<>(ticketService.cancelTicket(ticketId), HttpStatus.CREATED);
		} catch (TicketNotFoundException e) {
			logger.error("Ticket Not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}