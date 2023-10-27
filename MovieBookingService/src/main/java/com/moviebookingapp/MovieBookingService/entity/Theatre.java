package com.moviebookingapp.MovieBookingService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "theatre")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String theatreId;
	private String theatreName;
	private String theatreAddress;
	private String theatreCity;
	@OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Show> shows;
	@OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
	private List<Screen> screens;

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreAddress() {
		return theatreAddress;
	}

	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}

	public String getTheatreCity() {
		return theatreCity;
	}

	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}

	public String getTheatreId() {
		return theatreId;
	}

	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreAddress=" + theatreAddress
				+ ", theatreCity=" + theatreCity + ", shows=" + shows + ", screens=" + screens + "]";
	}

	public Theatre() {
	}

	public Theatre(String theatreName, String theatreAddress, String theatreCity, List<Show> shows,
			List<Screen> screens) {
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatreCity = theatreCity;
		this.shows = shows;
		this.screens = screens;
	}
}