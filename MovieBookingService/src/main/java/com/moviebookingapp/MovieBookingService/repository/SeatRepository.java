package com.moviebookingapp.MovieBookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Seat;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, String> {

}