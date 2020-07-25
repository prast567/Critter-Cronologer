package com.udacity.jdnd.course3.critter.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.SchedulePet;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulePetRepository extends CrudRepository<SchedulePet, Long> {

	SchedulePet findById(long id);
    
	List<SchedulePet> findSchedulePetsBySchedule(Schedule schedule);
	
	List<SchedulePet> findSchedulePetsByPet(Pet pet);

	@Query(value = "SELECT id FROM schedule_pet where pet_id in (SELECT id from pet where customer_id = :cusId)", nativeQuery = true)
    ArrayList<Long> findSchedulePetsByCustomerId(@Param("cusId") Long empid);
}