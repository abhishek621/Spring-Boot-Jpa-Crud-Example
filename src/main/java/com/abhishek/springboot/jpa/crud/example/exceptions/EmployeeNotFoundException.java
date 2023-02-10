package com.abhishek.springboot.jpa.crud.example.exceptions;
public class EmployeeNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmployeeNotFoundException(Long id) {
    super("Employee with id: " + id + " not found.");
  }
}
