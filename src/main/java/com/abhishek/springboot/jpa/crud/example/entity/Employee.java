package com.abhishek.springboot.jpa.crud.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery
			(name="firstProcedure",procedureName ="getEmployees"),
			@NamedStoredProcedureQuery(name="secondProcedure",
					procedureName = "getEmployeesByName",
						parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, 
											name = "fname", type = String.class)})
			})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "salary")
	private Double salary;

}
