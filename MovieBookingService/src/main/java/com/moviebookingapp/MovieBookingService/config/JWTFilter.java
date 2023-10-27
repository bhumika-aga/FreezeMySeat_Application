package com.moviebookingapp.MovieBookingService.config;

import java.io.IOException;
import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

	private final String SECRET = "6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";

	Logger logger = LoggerFactory.getLogger(JWTFilter.class);

	private Key getSecurityKey(String secret) {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest newRequest = (HttpServletRequest) request;
		String header = newRequest.getHeader("Authorization");
		String token = header.substring(7);
		if (header.equals(null) && !header.startsWith(header)) {
			throw new ServletException("Missing Header or the Header does not start with BEARER");
		}

		Claims claims = Jwts.parserBuilder().setSigningKey(getSecurityKey(SECRET)).build().parseClaimsJws(token)
				.getBody();
		newRequest.setAttribute("username", claims);
		logger.info("Do filter working");
		filterChain.doFilter(request, response);
	}
}