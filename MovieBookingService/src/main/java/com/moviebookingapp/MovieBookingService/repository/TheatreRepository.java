package com.moviebookingapp.MovieBookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, String> {

}