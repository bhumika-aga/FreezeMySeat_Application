package com.moviebookingapp.MovieBookingService.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Movie;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, String> {

	List<Movie> getMoviesByDate(LocalDate date);

	@Query(value = "Select m from Movie m where m.theatre.theatreId=:id")
	List<Movie> getMoviesByTheatre(String theatreId);
}