package com.abhishek.springboot.jpa.crud.example.entity;

import com.abhishek.springboot.jpa.crud.example.dto.EmployeeRequestDTO;
import com.abhishek.springboot.jpa.crud.example.dto.EmployeeResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueMapper {

	public static Employee convertToEntity(EmployeeRequestDTO employeeRequestDTO) {

		Employee employee = new Employee();
		employee.setFirstName(employeeRequestDTO.getFirstName());
		employee.setLastName(employeeRequestDTO.getLastName());
		employee.setEmail(employeeRequestDTO.getEmail());
		employee.setSalary(employeeRequestDTO.getSalary());
		employee.setCreatedOn(employeeRequestDTO.getCreatedOn());
		employee.setCreatedBy(employeeRequestDTO.getCreatedBy());
		employee.setUpdatedOn(employeeRequestDTO.getUpdatedOn());
		employee.setUpdatedBy(employeeRequestDTO.getUpdatedBy());

		return employee;
	}

	public static EmployeeResponseDTO convertToDTO(Employee employee) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
		employeeResponseDTO.setFirstName(employee.getFirstName());
		employeeResponseDTO.setLastName(employee.getLastName());
		employeeResponseDTO.setEmail(employee.getEmail());
		employeeResponseDTO.setSalary(employee.getSalary());
		employeeResponseDTO.setCreatedOn(employee.getCreatedOn());
		employeeResponseDTO.setCreatedBy(employee.getCreatedBy());
		employeeResponseDTO.setUpdatedOn(employee.getUpdatedOn());
		employeeResponseDTO.setUpdatedBy(employee.getUpdatedBy());
		return employeeResponseDTO;
	}

	public static String jsonAsString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
