package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.repository.CrudRepository;

import com.udacity.jdnd.course3.critter.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Customer findById(long id);
  
}