package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.EmployeeDayAvailable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDayAvailableRepository extends CrudRepository<EmployeeDayAvailable, Long> {

	EmployeeDayAvailable findById(long id);
  
}