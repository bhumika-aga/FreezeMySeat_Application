package com.moviebookingapp.MovieBookingService.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.moviebookingapp.MovieBookingService.entity.User;
import com.moviebookingapp.MovieBookingService.repository.UserRepository;

@Component
public class MyUserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals(null) || userRepository.findByUsername(username).equals(null)) {
			throw new UsernameNotFoundException("User does not exist!");
		}

		User user = userRepository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(user.getUserRole()))));
	}
}