package com.abhishek.springboot.jpa.crud.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.springboot.jpa.crud.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
