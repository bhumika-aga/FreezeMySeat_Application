package com.moviebookingapp.MovieBookingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {

	@Query(value = "select s from Show s join s.theatre t where t.theatreId= :theatreId")
	List<Show> getShowsByTheatreId(String theatreId);
}