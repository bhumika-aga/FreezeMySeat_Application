package com.moviebookingapp.FreezeMySeatServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.FreezeMySeatServer.entity.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select * from user where username = :username and password=:password")
	User validateUser(String username, String password);

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}