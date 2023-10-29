package com.moviebookingapp.MovieBookingService.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.MovieBookingService.entity.Show;
import com.moviebookingapp.MovieBookingService.exception.ScreenNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.ShowNotFoundException;
import com.moviebookingapp.MovieBookingService.exception.TheatreNotFoundException;
import com.moviebookingapp.MovieBookingService.repository.ScreenRepository;
import com.moviebookingapp.MovieBookingService.repository.ShowRepository;
import com.moviebookingapp.MovieBookingService.repository.TheatreRepository;
import com.moviebookingapp.MovieBookingService.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private ScreenRepository screenRepository;

	@Override
	public Show addShow(Show show, String theatreId, String screenId)
			throws TheatreNotFoundException, ScreenNotFoundException, Exception {
		if (show.equals(null) || !showRepository.findById(show.getShowId()).get().equals(null)) {
			throw new Exception("Show cannot be created!");
		} else if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		} else if (screenId.equals(null) || screenRepository.findById(screenId).get().equals(null)) {
			throw new ScreenNotFoundException("Screen does not exist!");
		}
		return showRepository.saveAndFlush(show);
	}

	@Override
	public List<Show> viewAllShows() {
		return showRepository.findAll();
	}

	@Override
	public Show viewShowById(String showId) throws ShowNotFoundException {
		if (showId.equals(null) || showRepository.findById(showId).get().equals(null)) {
			throw new ShowNotFoundException("Show cannot be updated!");
		}
		return showRepository.findById(showId).get();
	}

	@Override
	public List<Show> viewShowsByTheatre(String theatreId) throws TheatreNotFoundException {
		if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		}
		return showRepository.getShowsByTheatreId(theatreId);
	}

	@Override
	public List<Show> viewShowByDate(LocalDate date) throws ShowNotFoundException {
		List<Show> shows = new ArrayList<>();
		for (Show show : showRepository.findAll()) {
			if (!show.getShowDate().equals(null) || show.getShowDate().isEqual(date)) {
				shows.add(show);
			}
		}
		if (shows.isEmpty()) {
			throw new ShowNotFoundException("Show does not exist");
		}
		return shows;
	}

	@Override
	public Show updateShow(Show show, String theatreId, String screenId)
			throws ShowNotFoundException, TheatreNotFoundException, ScreenNotFoundException {
		if (show.equals(null) || showRepository.findById(show.getShowId()).get().equals(null)) {
			throw new ShowNotFoundException("Show cannot be updated!");
		} else if (theatreId.equals(null) || theatreRepository.findById(theatreId).get().equals(null)) {
			throw new TheatreNotFoundException("Theatre does not exist!");
		} else if (screenId.equals(null) || screenRepository.findById(screenId).get().equals(null)) {
			throw new ScreenNotFoundException("Screen does not exist!");
		}
		return showRepository.saveAndFlush(show);
	}

	@Override
	public boolean removeShow(String showId) throws ShowNotFoundException {
		if (showId.equals(null) || showRepository.findById(showId).get().equals(null)) {
			throw new ShowNotFoundException("Show cannot be updated!");
		}
		showRepository.deleteById(showId);
		return true;
	}
}