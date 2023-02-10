package com.abhishek.springboot.jpa.crud.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQueries({ @NamedStoredProcedureQuery(name = "firstProcedure", procedureName = "getEmployees"),
		@NamedStoredProcedureQuery(name = "secondProcedure", procedureName = "getEmployeesByName", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fname", type = String.class) }) })
@EntityListeners(AuditingEntityListener.class)
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

	@CreatedDate
	@Column(name = "created_on")
	private Date createdOn;
	@LastModifiedDate
	@Column(name = "updated_on")
	private Date updatedOn;
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	public Employee(long id, String firstName, String lastName, String email, Double salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;

	}

}
