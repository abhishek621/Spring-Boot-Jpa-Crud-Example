package com.abhishek.springboot.jpa.crud.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeRequestDTO {

	private String firstName;

	private String lastName;

	private String email;

	private double salary;

	private Date createdOn;

	private String createdBy;

	private Date updatedOn;

	private String updatedBy;

}
