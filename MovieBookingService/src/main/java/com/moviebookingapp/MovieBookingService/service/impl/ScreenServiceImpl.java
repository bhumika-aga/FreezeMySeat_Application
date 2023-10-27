package com.moviebookingapp.MovieBookingService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.moviebookingapp.MovieBookingService.entity.Screen;
import com.moviebookingapp.MovieBookingService.entity.Theatre;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.ScreenRepository;
import com.moviebookingapp.MovieBookingService.repository.TheatreRepository;
import com.moviebookingapp.MovieBookingService.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	@Override
	public Screen addScreen(Screen screen, String theatreId) throws TheatreNotFoundException, Exception {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		} else if (screen.equals(null) || !screenRepository.findById(screen.getScreenId()).equals(null)) {
			throw new Exception("Screen already exists!");
		}
		screen.setTheatre(theatreRepository.findById(theatreId).get());
		return screenRepository.saveAndFlush(screen);
	}

	@Override
	public List<Screen> viewAllScreens() {
		return screenRepository.findAll();
	}

	@Override
	public Screen viewScreen(String screenId) throws ScreenNotFoundException {
		if (screenId.equals(null) || screenRepository.findById(screenId).equals(null)) {
			throw new ScreenNotFoundException("Screen does not exist!");
		}
		return screenRepository.findById(screenId).get();
	}

	@Override
	public Theatre getTheatreOfScreen(String screenId) throws ScreenNotFoundException, TheatreNotFoundException {
		if (screenRepository.findById(screenId).get().getTheatre().getTheatreId().equals(null) || theatreRepository
				.findById(screenRepository.findById(screenId).get().getTheatre().getTheatreId()).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		} else if (screenId.equals(null) || screenRepository.findById(screenId).equals(null)) {
			throw new ScreenNotFoundException("Screen already exists!");
		}
		return screenRepository.findById(screenId).get().getTheatre();
	}

	@Override
	public Screen updateScreen(Screen screen, String theatreId)
			throws ScreenNotFoundException, TheatreNotFoundException {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		} else if (screen.equals(null) || !screenRepository.findById(screen.getScreenId()).equals(null)) {
			throw new ScreenNotFoundException("Screen already exists!");
		}
		return screenRepository.saveAndFlush(screen);
	}
}