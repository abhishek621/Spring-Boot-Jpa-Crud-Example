package com.abhishek.springboot.jpa.crud.example.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.springboot.jpa.crud.example.dto.InputRequest;
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

	public String createEmployee(InputRequest<Employee> request) {
		String currentUser = request.getLoggedInUser();
		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());

		Employee employee = request.getEmployee();
		employee.setCreatedBy(currentUser);
		employeeRepository.save(employee);
		return "Employee Saved Successfully";
	}

	public String updateEmployee(Long employeeId, InputRequest<Employee> request) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setFirstName(request.getEmployee().getFirstName());
		employee.setLastName(request.getEmployee().getLastName());
		employee.setEmail(request.getEmployee().getEmail());
		employee.setSalary(request.getEmployee().getSalary());
		employee.setUpdatedBy(request.getLoggedInUser());
		employeeRepository.saveAndFlush(employee);
		return "Employee Data Updated Successfully";
	}

	public Map<String, String> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Given Employee with id : " + employeeId + " is deleted");
		return response;
	}

}
