package com.moviebookingapp.MovieBookingService.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.moviebookingapp.MovieBookingService.entity.Movie;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.TheatreRepository;
import com.moviebookingapp.MovieBookingService.service.MovieService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/moviebooking/movie")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private KafkaMessageProducer producer;

	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie, @RequestParam(required = false) String showId,
			@RequestParam(required = false) String theatreId, @RequestHeader("Authorization") String auth)
			throws ShowNotFoundException, TheatreNotFoundException, Exception {
		try {
			if (jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Movie " + movie.getMovieName() + " created!");
			logger.info("Movie Added Successfully!");
			Movie newMovie = movieService.addMovieToShowAndTheatre(movie, showId, theatreId);
			return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
		} catch (TheatreNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ShowNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Movie Could Not Be Added!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> viewAllMovies() {
		try {
			logger.info("List of all Movies!");
			return new ResponseEntity<>(movieService.viewAllMovies(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<?> viewMovie(@PathVariable("movieId") String movieId) throws MovieNotFoundException {
		try {
			logger.info("Movie Found!");
			return new ResponseEntity<>(movieService.viewMovie(movieId), HttpStatus.FOUND);
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/byTheatre/{theatreId}")
	public ResponseEntity<?> viewMoviesByTheatre(@PathVariable("theatreId") String theatreId)
			throws MovieNotFoundException, TheatreNotFoundException {
		try {
			logger.info("Movie list for theatre " + theatreRepository.findById(theatreId).get().getTheatreName());
			return new ResponseEntity<>(movieService.viewMoviesByTheatre(theatreId), HttpStatus.FOUND);
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

	@GetMapping("/byDate/{date}")
	public ResponseEntity<?> viewMoviesByDate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
			throws MovieNotFoundException {
		try {
			logger.info("Movie list for date " + date);
			return new ResponseEntity<>(movieService.viewMoviesByDate(date), HttpStatus.FOUND);
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String auth)
			throws MovieNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			} else if (movie.equals(null)) {
				return new ResponseEntity<>("Please Enter Movie Details!", HttpStatus.NO_CONTENT);
			}
//			producer.pushMessage("Movie updated successfully!");
			logger.info("Movie Updated Successfully!");
			return new ResponseEntity<>(movieService.updateMovie(movie), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/remove/{movieId}")
	public ResponseEntity<?> removeMovie(@PathVariable("movieId") String movieId,
			@RequestHeader("Authorization") String auth) throws MovieNotFoundException {
		try {
			if (!jwtService.getRole(auth.substring(7)).equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity<>("Only Admins are allowed to perform this activity!", HttpStatus.FORBIDDEN);
			}
//			producer.pushMessage("Movie deleted");
			logger.info("Movie Removed!");
			return new ResponseEntity<>(movieService.removeMovie(movieId), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}