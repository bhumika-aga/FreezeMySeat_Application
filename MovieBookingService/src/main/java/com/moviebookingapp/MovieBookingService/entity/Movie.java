package com.moviebookingapp.MovieBookingService.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
@DynamicUpdate
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String movieId;
	private String movieName;
	private String movieGenre;
	private String movieDuration;
	private String movieDescription;
	private String moviePoster;
	private String movieBanner;
	private String trailerLink;
	private String movieLanguage;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate movieDate;
	@OneToOne
	@JsonIgnore
	private Show show;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(String movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getMovieBanner() {
		return movieBanner;
	}

	public void setMovieBanner(String movieBanner) {
		this.movieBanner = movieBanner;
	}

	public String getTrailerLink() {
		return trailerLink;
	}

	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public LocalDate getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getMovieId() {
		return movieId;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", movieGenre=" + movieGenre
				+ ", movieDuration=" + movieDuration + ", movieDescription=" + movieDescription + ", moviePoster="
				+ moviePoster + ", movieBanner=" + movieBanner + ", trailerLink=" + trailerLink + ", movieLanguage="
				+ movieLanguage + ", movieDate=" + movieDate + ", show=" + show + "]";
	}

	public Movie() {
	}

	public Movie(String movieName, String movieGenre, String movieDuration, String movieDescription, String moviePoster,
			String movieBanner, String trailerLink, String movieLanguage, LocalDate movieDate, Show show) {
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieDuration = movieDuration;
		this.movieDescription = movieDescription;
		this.moviePoster = moviePoster;
		this.movieBanner = movieBanner;
		this.trailerLink = trailerLink;
		this.movieLanguage = movieLanguage;
		this.movieDate = movieDate;
		this.show = show;
	}
}