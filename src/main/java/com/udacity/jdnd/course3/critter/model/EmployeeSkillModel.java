package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class EmployeeSkillModel {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;

	 private EmployeeSkill employeeSkill;
	 
	 @ManyToOne
	 @JoinColumn(name="employee_id", nullable=false)
	 private Employee employee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeSkill getEmployeeSkill() {
		return employeeSkill;
	}

	public void setEmployeeSkill(EmployeeSkill employeeSkill) {
		this.employeeSkill = employeeSkill;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	 
	 
	 
}	
