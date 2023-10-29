package com.moviebookingapp.MovieBookingService.service;

import java.time.LocalDate;
import java.util.List;

import com.moviebookingapp.MovieBookingService.entity.Movie;
import com.moviebookingapp.MovieBookingService.exception.MovieNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;

public interface MovieService {

	public Movie addMovie(Movie movie) throws Exception;

	public Movie addMovieToShowAndTheatre(Movie movie, String showId, String theatreId)
			throws MovieNotFoundException, ShowNotFoundException, TheatreNotFoundException;

	public List<Movie> viewAllMovies();

	public Movie viewMovie(String movieId) throws MovieNotFoundException;

	public List<Movie> viewMoviesByTheatre(String theatreId) throws MovieNotFoundException, TheatreNotFoundException;

	public List<Movie> viewMoviesByDate(LocalDate date) throws MovieNotFoundException;

	public Movie updateMovie(Movie movie) throws MovieNotFoundException;

	public boolean removeMovie(String movieId) throws MovieNotFoundException;
}