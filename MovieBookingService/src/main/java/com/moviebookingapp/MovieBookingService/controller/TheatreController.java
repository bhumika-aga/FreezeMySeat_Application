package com.moviebookingapp.MovieBookingService.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.MovieBookingService.config.JwtService;
import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.service.TheatreService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/theatre")
public class TheatreController {

	Logger logger = LoggerFactory.getLogger(TheatreController.class);

	@Autowired
	private TheatreService theatreService;

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private KafkaMessageProducer producer;

	@PostMapping("/addTheatre")
	public ResponseEntity<?> addTheatre(@RequestBody Theatre theatre, @RequestHeader("Authorization") String auth) {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Theatre Added!");
			logger.info("New theatre added successfully!");
			return new ResponseEntity<>(theatreService.addTheatre(theatre), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/allTheatres")
	public ResponseEntity<?> getAllTheatres() {
		try {
			logger.info("All theatres fetch!");
			return new ResponseEntity<>(theatreService.getAllTheatres(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/theatres/{theatreId}")
	public ResponseEntity<?> getTheatreById(@PathVariable("theatreId") String theatreId)
			throws TheatreNotFoundException {
		try {
			logger.info("Theatres fetch!");
			return new ResponseEntity<>(theatreService.getTheatreById(theatreId), HttpStatus.FOUND);
		} catch (TheatreNotFoundException e) {
			logger.error("Theatre not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/theatres/{movieId}")
	public ResponseEntity<?> getTheatreByMovie(@PathVariable("movieId") String movieId)
			throws TheatreNotFoundException, MovieNotFoundException {
		try {
			logger.info("Theatres found!");
			return new ResponseEntity<>(theatreService.getTheatreByMovie(movieId), HttpStatus.FOUND);
		} catch (TheatreNotFoundException e) {
			logger.error("Theatre not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateTheatre")
	public ResponseEntity<?> updateTheatre(@RequestBody Theatre theatre, @RequestHeader("Authorization") String auth)
			throws TheatreNotFoundException {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Theatre Updated!");
			logger.info("Theatre updated successfully!");
			return new ResponseEntity<>(theatreService.updateTheatre(theatre), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteTheatre/{theatreId}")
	public ResponseEntity<?> deleteTheatre(@PathVariable("theatreId") String theatreId,
			@RequestHeader("Authorization") String auth) {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Theatre Deleted!");
			logger.info("Theatre Deleted successfully!");
			return new ResponseEntity<>(theatreService.deleteTheatre(theatreId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}