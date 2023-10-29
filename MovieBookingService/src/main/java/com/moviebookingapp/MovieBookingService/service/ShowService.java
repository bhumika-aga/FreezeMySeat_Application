package com.moviebookingapp.MovieBookingService.service;

import java.time.LocalDate;
import java.util.List;

import com.moviebookingapp.MovieBookingService.entity.Show;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;

public interface ShowService {

	public Show addShow(Show show, String theatreId, String screenId)
			throws TheatreNotFoundException, ScreenNotFoundException, Exception;

	public List<Show> viewAllShows();

	public Show viewShowById(String showId) throws ShowNotFoundException;

	public List<Show> viewShowsByTheatre(String theatreId) throws TheatreNotFoundException;

	public List<Show> viewShowByDate(LocalDate date) throws ShowNotFoundException;

	public Show updateShow(Show show, String theatreId, String screenId)
			throws ShowNotFoundException, TheatreNotFoundException, ScreenNotFoundException;

	public boolean removeShow(String showId) throws ShowNotFoundException;
}