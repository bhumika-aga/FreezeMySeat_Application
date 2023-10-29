package com.moviebookingapp.MovieBookingService.service;

import java.util.List;

import com.moviebookingapp.MovieBookingService.entity.Screen;
import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;

public interface ScreenService {

	public Screen addScreen(Screen screen, String theatreId) throws TheatreNotFoundException, Exception;

	public List<Screen> viewAllScreens();

	public Screen viewScreen(String screenId) throws ScreenNotFoundException;

	public Theatre getTheatreOfScreen(String screenId) throws ScreenNotFoundException, TheatreNotFoundException;

	public Screen updateScreen(Screen screen, String theatreId)
			throws ScreenNotFoundException, TheatreNotFoundException;
}