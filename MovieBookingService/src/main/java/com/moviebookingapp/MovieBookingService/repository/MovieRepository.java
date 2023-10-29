package com.moviebookingapp.MovieBookingService.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

	@Query("SELECT m FROM Movie m WHERE m.movieDate = :date")
	List<Movie> getMoviesByDate(LocalDate date);

	@Query(value = "Select m from Movie m join m.theatre t where t.theatreId=:theatreId")
	List<Movie> getMoviesByTheatre(String theatreId);
}