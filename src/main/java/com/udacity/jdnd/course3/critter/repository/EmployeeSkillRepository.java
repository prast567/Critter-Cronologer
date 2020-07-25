package com.udacity.jdnd.course3.critter.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillRepository extends CrudRepository<EmployeeSkillModel, Long> {

	EmployeeSkillModel findById(long id);
	
	Set<EmployeeSkillModel> findByEmployeeSkill(EmployeeSkill employeeSkill);
	
	@Query(value = "SELECT employee_id FROM employee_skill_model WHERE employee_skill in :empskillids group by employee_id having count(DISTINCT employee_skill) = :empskillreqlength ", nativeQuery = true)
    ArrayList<Long> findEmployeeSkillModel(@Param("empskillids")List<Long> empskillids , @Param("empskillreqlength") Integer empskillreqlength);
	
}