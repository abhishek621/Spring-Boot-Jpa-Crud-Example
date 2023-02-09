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

import com.abhishek.springboot.jpa.crud.example.dto.InputRequest;
import com.abhishek.springboot.jpa.crud.example.entity.Employee;
import com.abhishek.springboot.jpa.crud.example.exceptions.ResourceNotFoundException;
import com.abhishek.springboot.jpa.crud.example.repo.EmployeeDao;
import com.abhishek.springboot.jpa.crud.example.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	//http://localhost:8080/swagger-ui/index.html
	
	@GetMapping("/firstProcedure")
	public List<Employee> findAllEmployeeUsingStoreProcedure(){
		return employeeDao.getAllEmployeeUsingStoreProcedure();
	}
	
	@GetMapping("/secondProcedure/{input}")
	public List<Employee> findEmployeeNameUsingStoreProcedure(@PathVariable(name="input")
									String input){
		return employeeDao.getEmployeeNameUsingStoreProcedure(input);
	}

	@GetMapping("/emp")
	public List<Employee> getAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with this : " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/emp")
	public String saveEmployee(@Valid @RequestBody InputRequest<Employee> employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/emp/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody InputRequest<Employee> employee) throws ResourceNotFoundException {
			String updatedEmployee = employeeService.updateEmployee(employeeId, employee);
		return ResponseEntity.ok(updatedEmployee);

	}

	@DeleteMapping("/emp/{id}")
	public Map<String, String> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		employeeService.deleteEmployee(employeeId);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Employee with id : "+ employeeId + " has been deleted");
		return response;
	}

}
