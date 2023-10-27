package com.moviebookingapp.MovieBookingService.exception;

import java.util.Date;

public class ErrorMapper {

	private String url;
	private String message;
	private Date now;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	@Override
	public String toString() {
		return "ErrorMapper [url=" + url + ", message=" + message + ", now=" + now + "]";
	}

	public ErrorMapper(String url, String message, Date now) {
		this.url = url;
		this.message = message;
		this.now = now;
	}
}