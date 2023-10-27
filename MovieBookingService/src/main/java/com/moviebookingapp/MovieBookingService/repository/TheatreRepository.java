package com.moviebookingapp.MovieBookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.Theatre;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TheatreRepository extends JpaRepository<Theatre, String> {

}