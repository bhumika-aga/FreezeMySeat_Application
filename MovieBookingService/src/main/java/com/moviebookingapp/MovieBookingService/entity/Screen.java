package com.moviebookingapp.MovieBookingService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String screenId;
	private String screenName;
	private int rows;
	private int columns;
	@ManyToOne
	@JsonIgnore
	private Theatre theatre;
	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
	private List<Show> shows;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public String getScreenId() {
		return screenId;
	}

	@Override
	public String toString() {
		return "Screen [screenId=" + screenId + ", screenName=" + screenName + ", rows=" + rows + ", columns=" + columns
				+ ", theatre=" + theatre + ", shows=" + shows + "]";
	}

	public Screen() {
	}

	public Screen(String screenName, int rows, int columns, Theatre theatre, List<Show> shows) {
		this.screenName = screenName;
		this.rows = rows;
		this.columns = columns;
		this.theatre = theatre;
		this.shows = shows;
	}
}