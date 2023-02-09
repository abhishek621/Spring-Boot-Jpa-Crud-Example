package com.abhishek.springboot.jpa.crud.example.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhishek.springboot.jpa.crud.example.entity.Employee;

@Repository
public class EmployeeDao{

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployeeUsingStoreProcedure(){
	return (List<Employee>) this.em.createNamedStoredProcedureQuery("firstProcedure")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeNameUsingStoreProcedure(String input){
	return (List<Employee>) this.em.createNamedStoredProcedureQuery("secondProcedure")
			.setParameter("fname", input)
			.getResultList();
	}
	 
}
