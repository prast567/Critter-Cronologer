package com.udacity.jdnd.course3.critter.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Employee findById(long id);
	
  
}