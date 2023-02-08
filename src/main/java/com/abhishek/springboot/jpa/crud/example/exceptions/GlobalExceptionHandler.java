package com.abhishek.springboot.jpa.crud.example.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails("NOT_FOUND");
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ExceptionResponse errorDetails = new ExceptionResponse();
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails("INTERNAL_SERVER_ERROR");
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResourceAlreadyExists ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails("CONFLICT");
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> customException(CustomException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails("BAD_REQUEST");
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails("UNAUTHORIZED");
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
	}
}
