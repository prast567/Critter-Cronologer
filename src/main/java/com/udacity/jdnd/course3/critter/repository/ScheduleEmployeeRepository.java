package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleEmployee;
import com.udacity.jdnd.course3.critter.model.SchedulePet;

public interface ScheduleEmployeeRepository extends CrudRepository<ScheduleEmployee, Long> {

	Employee findById(long id);
	List<ScheduleEmployee> findScheduleEmployeesBySchedule(Schedule schedule);
	
	List<ScheduleEmployee> findScheduleEmployeesByEmployee(Employee employee);
}