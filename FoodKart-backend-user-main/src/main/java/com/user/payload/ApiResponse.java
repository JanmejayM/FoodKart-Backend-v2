package com.user.payload;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	
	private String message;
	private LocalDate date;
	private HttpStatus status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponse(String message, LocalDate date, HttpStatus status) {
		super();
		this.message = message;
		this.date = date;
		this.status = status;
	}
	
	
	
	
	
	
	

}
