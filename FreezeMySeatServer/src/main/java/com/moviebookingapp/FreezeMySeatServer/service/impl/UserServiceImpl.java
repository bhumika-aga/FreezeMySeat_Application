package com.moviebookingapp.FreezeMySeatServer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.moviebookingapp.FreezeMySeatServer.entity.User;
import com.moviebookingapp.FreezeMySeatServer.exception.UserCreationException;
import com.moviebookingapp.FreezeMySeatServer.exception.UserNotFoundException;
import com.moviebookingapp.FreezeMySeatServer.repository.UserRepository;
import com.moviebookingapp.FreezeMySeatServer.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) throws UserCreationException {
		if (user.equals(null) || !userRepository.findById(user.getUserId()).get().equals(null)) {
			throw new UserCreationException("User could not be created!");
		}
		if (user.getUserRole().equals(null)) {
			user.setUserRole("USER");
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		if (userRepository.findAll().isEmpty()) {
			throw new UserNotFoundException("User does not exist!");
		}
		return userRepository.findAll();
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		if (username.equals(null) || userRepository.findByUsername(username).equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		return userRepository.findByUsername(username);
	}

	@Override
	public User getUser(String username, String password) throws UserNotFoundException {
		if (username.equals(null) || password.equals(null)
				|| userRepository.findByUsernameAndPassword(username, password).equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String getUserRole(String username, String password) throws UserNotFoundException {
		if (username.equals(null) || password.equals(null)
				|| userRepository.findByUsernameAndPassword(username, password).equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		return userRepository.findByUsernameAndPassword(username, password).getUserRole();
	}

	@Override
	public boolean loginUser(String username, String password) throws UserNotFoundException {
		if (username.equals(null) || password.equals(null)
				|| userRepository.findByUsernameAndPassword(username, password).equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		User user = userRepository.validateUser(username, password);
		if (user.equals(null)) {
			return false;
		}
		System.out.println("User: " + user.getUsername() + " is Logged In!");
		return true;
	}

	@Override
	public boolean forgotPassword(String username, String email, String password) throws UserNotFoundException {
		if (username.equals(null) || password.equals(null)
				|| userRepository.findByUsernameAndPassword(username, password).equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		User user = userRepository.findByUsername(username);
		if (user.equals(null) || !user.getEmail().equals(email)) {
			return false;
		}
		user.setPassword(password);
		userRepository.saveAndFlush(user);
		System.out.println("password Reset Successful for: " + user);
		return false;
	}

	@Override
	public User updateUser(User user) throws UserNotFoundException {
		if (user.equals(null) || userRepository.findById(user.getUserId()).get().equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public boolean deleteUser(String userId) throws UserNotFoundException {
		if (userId.equals(null) || userRepository.findById(userId).get().equals(null)) {
			throw new UserNotFoundException("User does not exist!");
		}
		userRepository.deleteById(userId);
		return true;
	}
}