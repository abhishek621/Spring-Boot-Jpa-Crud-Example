package com.abhishek.springboot.jpa.crud.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDTO {

	private String firstName;

	private String lastName;

	private String email;

	private double salary;

	private Date createdOn;

	private String createdBy;

	private Date updatedOn;

	private String updatedBy;
}
