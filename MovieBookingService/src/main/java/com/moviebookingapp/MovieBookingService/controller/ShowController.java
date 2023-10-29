package com.moviebookingapp.MovieBookingService.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.entity.Show;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.service.ShowService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/show")
public class ShowController {

	Logger logger = LoggerFactory.getLogger(ShowController.class);

	@Autowired
	private ShowService showService;

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private KafkaMessageProducer producer;

	@PostMapping("/add")
	public ResponseEntity<?> addShow(@RequestBody Show show, @RequestParam(required = false) String theatreId,
			@RequestParam(required = false) String screenId, @RequestHeader("Authorization") String auth)
			throws TheatreNotFoundException, ScreenNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Show added!");
			logger.info("Show added successfully!");
			return new ResponseEntity<>(showService.addShow(show, theatreId, screenId), HttpStatus.CREATED);
		} catch (TheatreNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ScreenNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewAll")
	public ResponseEntity<?> viewAllShows() {
		try {
			logger.info("Show list!");
			return new ResponseEntity<>(showService.viewAllShows(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewShow/{showId}")
	public ResponseEntity<?> viewShowById(@PathVariable("showId") String showId) throws ShowNotFoundException {
		try {
			return new ResponseEntity<>(showService.viewShowById(showId), HttpStatus.FOUND);
		} catch (ShowNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewShows/{theatreId}")
	public ResponseEntity<?> viewShowByTheatre(@PathVariable("theatreId") String theatreId)
			throws TheatreNotFoundException {
		try {
			return new ResponseEntity<>(showService.viewShowsByTheatre(theatreId), HttpStatus.FOUND);
		} catch (TheatreNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/viewShows/{date}")
	public ResponseEntity<?> viewShowByDate(@PathVariable("date") LocalDate date) throws ShowNotFoundException {
		try {
			return new ResponseEntity<>(showService.viewShowByDate(date), HttpStatus.FOUND);
		} catch (ShowNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateShow(@RequestBody Show show, @RequestParam(required = false) String theatreId,
			@RequestParam(required = false) String screenId, @RequestHeader("Authorization") String auth)
			throws ShowNotFoundException, TheatreNotFoundException, ScreenNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Show updated!");
			logger.info("Show updated successfully!");
			return new ResponseEntity<>(showService.updateShow(show, theatreId, screenId), HttpStatus.OK);
		} catch (TheatreNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ScreenNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ShowNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/remove/{showId}")
	public ResponseEntity<?> removeShow(@PathVariable("showId") String showId,
			@RequestHeader("Authorization") String auth) throws ShowNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Show removed");
			logger.info("Show removed successfully!");
			return new ResponseEntity<>(showService.removeShow(showId), HttpStatus.OK);
		} catch (ShowNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}