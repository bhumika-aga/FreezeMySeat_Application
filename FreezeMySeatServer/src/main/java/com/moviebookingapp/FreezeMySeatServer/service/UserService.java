package com.moviebookingapp.FreezeMySeatServer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.FreezeMySeatServer.entity.User;
import com.moviebookingapp.FreezeMySeatServer.exception.UserCreationException;
import com.moviebookingapp.FreezeMySeatServer.exception.UserNotFoundException;

@Service
public interface UserService {

	public User addUser(User user) throws UserCreationException;

	public List<User> getAllUsers() throws UserNotFoundException;

	public User getUser(String username) throws UserNotFoundException;

	public User getUser(String username, String password) throws UserNotFoundException;

	public String getUserRole(String username, String password) throws UserNotFoundException;

	public boolean loginUser(String username, String password) throws UserNotFoundException;

	public boolean forgotPassword(String username, String email, String password) throws UserNotFoundException;

	public User updateUser(User user) throws UserNotFoundException;

	public boolean deleteUser(String userId) throws UserNotFoundException;
}