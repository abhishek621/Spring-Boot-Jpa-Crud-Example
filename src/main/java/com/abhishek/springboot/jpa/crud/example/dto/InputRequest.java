package com.abhishek.springboot.jpa.crud.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InputRequest<T> {

	@JsonProperty(value = "userId", required = true)
	private String loggedInUser;
	private String timeZone;
	private T employee;
}
