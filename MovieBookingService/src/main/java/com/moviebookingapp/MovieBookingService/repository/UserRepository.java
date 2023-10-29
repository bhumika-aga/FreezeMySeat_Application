package com.moviebookingapp.MovieBookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.MovieBookingService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	User validateUser(@Param("username") String username, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.username=:username")
	User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	User findByUsernameAndPassword(String username, String password);
}