package com.moviebookingapp.MovieBookingService.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Movie;
import com.moviebookingapp.MovieBookingService.entity.Show;
import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.MovieRepository;
import com.moviebookingapp.MovieBookingService.repository.ShowRepository;
import com.moviebookingapp.MovieBookingService.repository.TheatreRepository;
import com.moviebookingapp.MovieBookingService.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private ShowRepository showRepository;

	@Override
	public Movie addMovie(Movie movie) throws Exception {
		if (movie.equals(null) || movieRepository.findById(movie.getMovieId()).get().equals(movie)) {
			throw new Exception("Movie could not be created!");
		}
		return movieRepository.saveAndFlush(movie);
	}

	@Override
	public Movie addMovieToShowAndTheatre(Movie movie, String showId, String theatreId)
			throws MovieNotFoundException, ShowNotFoundException, TheatreNotFoundException {
		if (showId.equals(null) || showRepository.findById(showId).get().equals(null)) {
			throw new ShowNotFoundException("Show does not exist!");
		} else if (theatreId.equals(null) || theatreRepository.findById(theatreId).equals(null)) {
			throw new MovieNotFoundException("Theatre does not exist");
		} else if (movie.equals(null) || movieRepository.findById(movie.getMovieId()).get().equals(null)) {
			throw new MovieNotFoundException("Movie does not exist");
		}
		Show show = showRepository.findById(showId).get();
		Theatre theatre = theatreRepository.findById(theatreId).get();
		movie.setShow(show);
		movie.setTheatre(theatre);
		return movieRepository.saveAndFlush(movie);
	}

	@Override
	public List<Movie> viewAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie viewMovie(String movieId) throws MovieNotFoundException {
		if (movieId.equals(null) || movieRepository.findById(movieId).get().equals(null)) {
			throw new MovieNotFoundException("Movie does not exist!");
		}
		return movieRepository.findById(movieId).get();
	}

	@Override
	public List<Movie> viewMoviesByTheatre(String theatreId) throws MovieNotFoundException, TheatreNotFoundException {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre Does Not Exist!");
		}
		List<Movie> movies = new ArrayList<>();
		Set<String> showIds = new HashSet<>();
		List<Show> shows = showRepository.findAll();
		for (Show show : shows) {
			if (show.getTheatre().getTheatreId().equals(theatreId)) {
				showIds.add(show.getShowId());
			}
		}
		for (String id : showIds) {
			movies.add(showRepository.findById(id).get().getMovie());
		}
		if (movies.isEmpty()) {
			throw new MovieNotFoundException("Movie Does not exist!");
		}
		return movies;
	}

	@Override
	public List<Movie> viewMoviesByDate(LocalDate date) throws MovieNotFoundException {
		List<Movie> movies = new ArrayList<>();
		for (Movie movie : movieRepository.findAll()) {
			if (!movie.getMovieDate().equals(null) || movie.getMovieDate().isEqual(date)) {
				movies.add(movie);
			}
		}
		if (movies.isEmpty()) {
			throw new MovieNotFoundException("Movie Does not exist!");
		}
		return movies;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		if (movie.equals(null) || movieRepository.findById(movie.getMovieId()).get().equals(null)) {
			throw new MovieNotFoundException("Movie Does Not Exist!");
		}
		return movieRepository.saveAndFlush(movie);
	}

	@Override
	public boolean removeMovie(String movieId) throws MovieNotFoundException {
		if (movieId.equals(null) || movieRepository.findById(movieId).equals(null)) {
			throw new MovieNotFoundException("Movie Does Not Exist!");
		}
		movieRepository.deleteById(movieId);
		return true;
	}
}