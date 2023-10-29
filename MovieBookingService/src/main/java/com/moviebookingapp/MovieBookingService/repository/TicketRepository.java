package com.moviebookingapp.MovieBookingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

	@Query("Select t from Ticket t join t.user s where s.username=:username")
	List<Ticket> getTicketByUser(String username);
}