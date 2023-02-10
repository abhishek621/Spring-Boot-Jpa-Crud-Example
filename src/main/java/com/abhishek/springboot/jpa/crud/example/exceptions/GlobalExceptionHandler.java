package com.abhishek.springboot.jpa.crud.example.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse("EMPLOYEE_NOT_FOUND", ex.getMessage(),new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
 