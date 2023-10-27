package com.moviebookingapp.MovieBookingService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.moviebookingapp.MovieBookingService.entity.Screen;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.service.ScreenService;
import com.moviebookingapp.MovieBookingService.service.impl.KafkaMessageProducer;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/screen")
public class ScreenController {

	Logger logger = LoggerFactory.getLogger(ScreenController.class);

	@Autowired
	private ScreenService screenService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private KafkaMessageProducer producer;

	@PostMapping("/addScreen")
	public ResponseEntity<?> addScreen(@RequestBody Screen screen, @RequestParam(required = false) String theatreId,
			@RequestHeader("Authorization") String auth) throws TheatreNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			producer.pushMessage("Screen added!");
			logger.info("Screen added to theatre!");
			return new ResponseEntity<>(screenService.addScreen(screen, theatreId), HttpStatus.CREATED);
		} catch (TheatreNotFoundException e) {
			logger.error("Theatre cannot be found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/allScreens")
	public ResponseEntity<?> viewAllScreens(@RequestHeader("Authorization") String auth) {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			logger.info("List of All Screens");
			return new ResponseEntity<>(screenService.viewAllScreens(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findScreen/{screenId}")
	public ResponseEntity<?> viewScreen(@PathVariable("screenId") String screenId,
			@RequestHeader("Authorization") String auth) throws ScreenNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			logger.info("Screen Found!");
			return new ResponseEntity<>(screenService.viewScreen(screenId), HttpStatus.FOUND);
		} catch (ScreenNotFoundException e) {
			logger.error("Screen could not be found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findByTheatre/{screenId}")
	public ResponseEntity<?> getTheatreOfScreen(@PathVariable("screenId") String screenId,
			@RequestHeader("Authorization") String auth) throws ScreenNotFoundException, TheatreNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			logger.info("The Screen belongs to the theatre!");
			return new ResponseEntity<>(screenService.getTheatreOfScreen(screenId), HttpStatus.FOUND);
		} catch (ScreenNotFoundException e) {
			logger.error("Screen could not be found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (TheatreNotFoundException e) {
			logger.error("Theatre not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateScreen(@RequestBody Screen screen, @RequestParam(required = false) String theatreId,
			@RequestHeader("Authorization") String auth) throws ScreenNotFoundException, TheatreNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
			producer.pushMessage("Screen updated!");
			logger.info("Screen updated!");
			return new ResponseEntity<>(screenService.updateScreen(screen, theatreId), HttpStatus.FOUND);
		} catch (ScreenNotFoundException e) {
			logger.error("Screen could not be found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (TheatreNotFoundException e) {
			logger.error("Theatre not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}