package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleSkill;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleSkillRepository extends CrudRepository<ScheduleSkill, Long> {

	EmployeeSkillModel findById(long id);
	
	List<ScheduleSkill> findScheduleSkillsBySchedule(Schedule schedule);
	
}