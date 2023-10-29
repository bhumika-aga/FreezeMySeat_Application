package com.moviebookingapp.MovieBookingService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.entity.Seat;
import com.moviebookingapp.MovieBookingService.exception.SeatNotFoundException;
import com.moviebookingapp.MovieBookingService.service.SeatService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/seat")
public class SeatController {

	Logger logger = LoggerFactory.getLogger(SeatController.class);

	@Autowired
	private SeatService seatService;

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private KafkaMessageProducer producer;

	@PostMapping("/addSeat")
	public ResponseEntity<?> addSeat(@RequestBody Seat seat, @RequestHeader("Authorization") String auth)
			throws SeatNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Seat added!");
			logger.info("Seat Added Successfully!");
			return new ResponseEntity<>(seatService.addSeat(seat), HttpStatus.CREATED);
		} catch (SeatNotFoundException e) {
			logger.error("Seat could not be added!");
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> viewAllSeats(@RequestHeader("Authorization") String auth) {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			logger.info("Seat list!");
			return new ResponseEntity<>(seatService.viewAllSeats(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/block")
	public ResponseEntity<?> blockSeat(@RequestBody Seat seat) throws SeatNotFoundException {
		try {
			return new ResponseEntity<>(seatService.blockSeat(seat), HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			logger.error("Seat could not be added!");
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/book")
	public ResponseEntity<?> bookSeat(@RequestBody Seat seat) throws SeatNotFoundException {
		try {
			return new ResponseEntity<>(seatService.bookSeat(seat), HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			logger.error("Seat could not be added!");
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/cancel")
	public ResponseEntity<?> cancelSeat(@RequestBody Seat seat) throws SeatNotFoundException {
		try {
			return new ResponseEntity<>(seatService.cancelSeatBooking(seat), HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			logger.error("Seat could not be added!");
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateSeat(@RequestBody Seat seat, @RequestHeader("Authorization") String auth)
			throws SeatNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Seat updated!");
			logger.info("Seat updated successfully!");
			return new ResponseEntity<>(seatService.updateSeat(seat), HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			logger.error("Seat could not be added!");
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}