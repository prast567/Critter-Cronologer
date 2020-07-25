package com.udacity.jdnd.course3.critter.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

	Schedule findById(long id);
	
}