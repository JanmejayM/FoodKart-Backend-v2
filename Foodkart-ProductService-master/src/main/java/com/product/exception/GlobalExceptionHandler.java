package com.product.exception;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.product.payload.ApiResponse;




@RestControllerAdvice
public class GlobalExceptionHandler{
	
	
	

	@ExceptionHandler(value=ProductNotFoundException.class)
	public ResponseEntity<ApiResponse> ProductNotFoundException(ProductNotFoundException ex)
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
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<ApiResponse> UserNotFoundException(UserNotFoundException ex)
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

	@ExceptionHandler(value=javax.validation.ConstraintViolationException.class)
	public ResponseEntity<ApiResponse> ConstraintViolationException(javax.validation.ConstraintViolationException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.BAD_REQUEST);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}

}
