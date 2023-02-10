package com.abhishek.springboot.jpa.crud.example.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.springboot.jpa.crud.example.dto.InputRequest;
import com.abhishek.springboot.jpa.crud.example.entity.Employee;
import com.abhishek.springboot.jpa.crud.example.repo.EmployeeDao;
import com.abhishek.springboot.jpa.crud.example.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDao employeeDao;

	// http://localhost:8080/swagger-ui/index.html

	@GetMapping("/firstProcedure")
	public ResponseEntity<List<Employee>> findAllEmployeeUsingStoreProcedure() {
		List<Employee> usingStoreProcedure = employeeDao.getAllEmployeeUsingStoreProcedure();
		return ResponseEntity.ok(usingStoreProcedure);
	}

	@GetMapping("/secondProcedure/{input}")
	public ResponseEntity<List<Employee>> findEmployeeNameUsingStoreProcedure(
			@PathVariable(name = "input") String input) {
		List<Employee> usingStoreProcedure = employeeDao.getEmployeeNameUsingStoreProcedure(input);
		if (!usingStoreProcedure.isEmpty()) {
			return ResponseEntity.ok(usingStoreProcedure);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/emp")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody InputRequest<Employee> employee) {
		String createdEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	}

	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		if (employee == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@PutMapping("/emp/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody InputRequest<Employee> employee) {
		String updatedEmployee = employeeService.updateEmployee(employeeId, employee);
		return ResponseEntity.ok(updatedEmployee);

	}

	@DeleteMapping("/emp/{id}")
	public Map<String, String> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}

}
