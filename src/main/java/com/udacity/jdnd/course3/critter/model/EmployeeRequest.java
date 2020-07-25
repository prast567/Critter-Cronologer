package com.udacity.jdnd.course3.critter.model;

import java.time.LocalDate;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

public class EmployeeRequest {

	private long id;

    private LocalDate date;
    
    private Set<EmployeeSkillModel> skills;
	
}
