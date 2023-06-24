package com.user.exception;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import org.hibernate.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.user.payload.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler{
	
	
	

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<ApiResponse> UserNotFound(UserNotFoundException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.UNAUTHORIZED);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	public ResponseEntity<ApiResponse> UserAlreadyExistsException(UserAlreadyExistsException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value=InvalidAddressException.class)
	public ResponseEntity<ApiResponse> InvalidAddressException(InvalidAddressException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.NOT_FOUND);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.BAD_REQUEST);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.BAD_REQUEST);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.BAD_REQUEST);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	

}
