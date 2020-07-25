package com.udacity.jdnd.course3.critter.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

	Pet findById(long id);
    
	Set<Pet> findPetsByOwner(Customer customer);
	
}