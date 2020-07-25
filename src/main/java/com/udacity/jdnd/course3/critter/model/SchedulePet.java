package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class SchedulePet {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;

	 @ManyToOne
	 @JoinColumn(name="pet_id", nullable=true)
	 private Pet pet;
	 
	 @ManyToOne
	 @JoinColumn(name="schedule_id", nullable=true)
	 private Schedule schedule;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


	 
}	

