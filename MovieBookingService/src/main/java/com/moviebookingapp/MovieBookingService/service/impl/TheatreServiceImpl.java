package com.moviebookingapp.MovieBookingService.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.moviebookingapp.MovieBookingService.entity.Show;
import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.MovieRepository;
import com.moviebookingapp.MovieBookingService.repository.TheatreRepository;
import com.moviebookingapp.MovieBookingService.service.TheatreService;

public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Theatre addTheatre(Theatre theatre) throws Exception {
		if (theatre.equals(null) || !theatreRepository.findById(theatre.getTheatreId()).get().equals(null)) {
			throw new Exception("Theatre Could not be added!");
		}
		return theatreRepository.saveAndFlush(theatre);
	}

	@Override
	public List<Theatre> getAllTheatres() {
		return theatreRepository.findAll();
	}

	@Override
	public Theatre getTheatreById(String theatreId) throws TheatreNotFoundException {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		}
		return theatreRepository.findById(theatreId).get();
	}

	@Override
	public List<Theatre> getTheatreByMovie(String movieId) throws TheatreNotFoundException, MovieNotFoundException {
		if (movieId.equals(null) || movieRepository.findById(movieId).get().equals(null)) {
			throw new MovieNotFoundException("Movie does not exist!");
		}
		List<Theatre> theatres = new ArrayList<>();
		for (Theatre theatre : theatreRepository.findAll()) {
			List<Show> shows = theatre.getShows();
			for (Show show : shows) {
				if (show.getShowId().equals(movieRepository.findById(movieId).get().getShow().getShowId())) {
					theatres.add(theatre);
				}
			}
		}
		if (theatres.isEmpty()) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		}
		return theatres;
	}

	@Override
	public Theatre updateTheatre(Theatre theatre) throws TheatreNotFoundException {
		if (theatre.equals(null) || theatreRepository.findById(theatre.getTheatreId()).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		}
		return theatreRepository.saveAndFlush(theatre);
	}

	@Override
	public boolean deleteTheatre(String theatreId) throws TheatreNotFoundException {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		}
		theatreRepository.deleteById(theatreId);
		return true;
	}
}