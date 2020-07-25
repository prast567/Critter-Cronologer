package com.udacity.jdnd.course3.critter.model;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeDayAvailable {
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;

	 private DayOfWeek daysAvailable;
	 
	 @ManyToOne
	 @JoinColumn(name="employee_id", nullable=false)
	 private Employee employee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DayOfWeek getDaysAvailable() {
		return daysAvailable;
	}

	public void setDaysAvailable(DayOfWeek daysAvailable) {
		this.daysAvailable = daysAvailable;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	 
	 
	 
}
