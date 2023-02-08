package com.abhishek.springboot.jpa.crud.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.springboot.jpa.crud.example.entity.Employee;
import com.abhishek.springboot.jpa.crud.example.exceptions.ResourceNotFoundException;
import com.abhishek.springboot.jpa.crud.example.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/emp")
	public List<Employee> getAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/emp")
	public Employee saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/emp/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

		Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDetails);

		return ResponseEntity.ok(updatedEmployee);

	}

	@DeleteMapping("/emp/{id}")
	public Map<String, String> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		employeeService.deleteEmployee(employeeId);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Given Employee with id : "+ employeeId + " is deleted");
		return response;
	}

}
