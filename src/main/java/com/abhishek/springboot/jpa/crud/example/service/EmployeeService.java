package com.abhishek.springboot.jpa.crud.example.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.springboot.jpa.crud.example.dto.EmployeeDTO;
import com.abhishek.springboot.jpa.crud.example.dto.InputRequest;
import com.abhishek.springboot.jpa.crud.example.entity.Employee;
import com.abhishek.springboot.jpa.crud.example.exceptions.EmployeeNotFoundException;
import com.abhishek.springboot.jpa.crud.example.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(long employeeId) {
		return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
	}

	public String createEmployee(InputRequest<Employee> request) {
		String currentUser = request.getLoggedInUser();
		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());

		Employee employee = request.getEmployee();
		employee.setCreatedBy(currentUser);
		employeeRepository.save(employee);
		return "Employee Saved Successfully";
	}

	public String updateEmployee(Long id, InputRequest<Employee> request) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));

		existingEmployee.setFirstName(request.getEmployee().getFirstName());
		existingEmployee.setLastName(request.getEmployee().getLastName());
		existingEmployee.setEmail(request.getEmployee().getEmail());
		existingEmployee.setSalary(request.getEmployee().getSalary());
		existingEmployee.setUpdatedBy(request.getLoggedInUser());
		employeeRepository.saveAndFlush(existingEmployee);
		return "Employee Data Updated Successfully";
	}

	
	 public Map<String,String> deleteEmployee(Long employeeId) {
		    employeeRepository.findById(employeeId)
		        .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
		    employeeRepository.deleteById(employeeId);
		    Map<String, String> response = new HashMap<>();
		    response.put("message", "Given Employee with id : " + employeeId + " is deleted");
		    return response;
	 }

	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = mapDtoToEntity(employeeDTO);
		employee = employeeRepository.save(employee);
		return mapEntityToDto(employee);
	}	

	private Employee mapDtoToEntity(EmployeeDTO employeeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employeeDTO, Employee.class);
	}

	private EmployeeDTO mapEntityToDto(Employee employee) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employee, EmployeeDTO.class);
	}

}
