package com.udacity.jdnd.course3.critter.model;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Employee {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 private String name;

	 @OneToMany(mappedBy="employee")
	 private Set<EmployeeSkillModel> skills;
	 
	 @OneToMany(mappedBy="employee")
	 private Set<EmployeeDayAvailable> daysAvailable;

	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmployeeSkillModel> getSkills() {
		return skills;
	}

	public void setSkills(Set<EmployeeSkillModel> skills) {
		this.skills = skills;
	}

	public Set<EmployeeDayAvailable> getDaysAvailable() {
		return daysAvailable;
	}
	
	public Set<DayOfWeek> getDaysAvailableSetOnly() {
		Set<DayOfWeek> dowList = new HashSet<DayOfWeek>();
		
		if(null == this.getDaysAvailable()){
			return null;
		}
		for(EmployeeDayAvailable eda: this.getDaysAvailable()){
			dowList.add(eda.getDaysAvailable());
		}
		return dowList;
	}

	public void setDaysAvailable(Set<EmployeeDayAvailable> daysAvailable) {
		this.daysAvailable = daysAvailable;
	}
	 
	public Set<EmployeeSkill> getEmployeeSkillsSetOnly() {
		Set<EmployeeSkill> esList = new HashSet<EmployeeSkill>();
		
		if(null == this.getSkills()){
			return null;
		}
		for(EmployeeSkillModel es: this.getSkills()){
			esList.add(es.getEmployeeSkill());
		}
		return esList;
	}
}
