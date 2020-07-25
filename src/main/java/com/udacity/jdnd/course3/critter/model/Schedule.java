package com.udacity.jdnd.course3.critter.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Schedule {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(mappedBy="schedule")
	private Set<ScheduleEmployee> employees;
	@OneToMany(mappedBy="schedule")
	private Set<SchedulePet> pets;
	
    private LocalDate date;

	@OneToMany(mappedBy="schedule")
	private Set<ScheduleSkill> skills;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<ScheduleEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<ScheduleEmployee> employees) {
		this.employees = employees;
	}

	public Set<SchedulePet> getPets() {
		return pets;
	}

	public void setPets(Set<SchedulePet> pets) {
		this.pets = pets;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<ScheduleSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<ScheduleSkill> skills) {
		this.skills = skills;
	}
	
	
}
