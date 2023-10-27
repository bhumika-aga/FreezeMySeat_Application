package com.moviebookingapp.MovieBookingService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;

@Service
public interface TheatreService {

	public Theatre addTheatre(Theatre theatre) throws Exception;

	public List<Theatre> getAllTheatres();

	public Theatre getTheatreById(String theatreId) throws TheatreNotFoundException;

	public List<Theatre> getTheatreByMovie(String movieId) throws TheatreNotFoundException, MovieNotFoundException;

	public Theatre updateTheatre(Theatre theatre) throws TheatreNotFoundException;

	public boolean deleteTheatre(String theatreId) throws TheatreNotFoundException;
}