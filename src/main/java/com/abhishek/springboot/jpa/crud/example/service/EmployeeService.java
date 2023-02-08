package com.abhishek.springboot.jpa.crud.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.springboot.jpa.crud.example.entity.Employee;
import com.abhishek.springboot.jpa.crud.example.exceptions.ResourceNotFoundException;
import com.abhishek.springboot.jpa.crud.example.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(long employeeId) {
		return employeeRepository.findById(employeeId);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public Map<String, String> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Given Employee with id : "+ employeeId + " is deleted");
		return response;
	}

}
