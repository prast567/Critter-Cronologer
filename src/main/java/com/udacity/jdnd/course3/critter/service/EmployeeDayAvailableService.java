package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeDayAvailable;
import com.udacity.jdnd.course3.critter.repository.EmployeeDayAvailableRepository;

@Component
public class EmployeeDayAvailableService {

	@Autowired
	EmployeeDayAvailableRepository employeeDayAvailableRepository;
	
	public EmployeeDayAvailable add(EmployeeDayAvailable employeeDayAvailable, Employee employee) {
		EmployeeDayAvailable esm = new EmployeeDayAvailable();
		esm.setEmployee(employee);
		esm.setDaysAvailable(employeeDayAvailable.getDaysAvailable());
		return employeeDayAvailableRepository.save(esm);
    }
	
	public Set<EmployeeDayAvailable> addMultiple(EmployeeDTO employeeDTO, Employee employee) {
		
		Set<EmployeeDayAvailable> edaList = new HashSet<EmployeeDayAvailable>();
		
		if(null != employeeDTO.getDaysAvailable()) {
			for(DayOfWeek dayOfWeek : employeeDTO.getDaysAvailable()) {
				
				EmployeeDayAvailable edaTemp = new EmployeeDayAvailable();
				edaTemp.setEmployee(employee);
				edaTemp.setDaysAvailable(dayOfWeek);
				employeeDayAvailableRepository.save(edaTemp);
				
				edaList.add(edaTemp);
				
			}
		}
		
		if(edaList.size() == 0) {
			edaList = null;
		}
		
		return edaList;
    }
   
    public List<EmployeeDayAvailable> getAll() {
    	List<EmployeeDayAvailable> list = new ArrayList<>();
    	employeeDayAvailableRepository.findAll().forEach(list::add);
    	return list;
    }
}
