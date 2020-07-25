package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;

@Component
public class EmployeeSkillService {

	@Autowired
	EmployeeSkillRepository employeeSkillRepository;
	
	public EmployeeSkillModel add(EmployeeSkill employeeSkill, Employee employee) {
		EmployeeSkillModel esm = new EmployeeSkillModel();
		esm.setEmployee(employee);
		esm.setEmployeeSkill(employeeSkill);
		return employeeSkillRepository.save(esm);
    }
	
	public Set<EmployeeSkillModel> addMultiple(EmployeeDTO employeeDTO, Employee employee) {
		
		Set<EmployeeSkillModel> esmList = new HashSet<EmployeeSkillModel>();
		
		if(null != employeeDTO.getSkills()) {
			for(EmployeeSkill es : employeeDTO.getSkills()) {
				
				EmployeeSkillModel esmTemp = new EmployeeSkillModel();
				esmTemp.setEmployee(employee);
				esmTemp.setEmployeeSkill(es);
				employeeSkillRepository.save(esmTemp);
				
				esmList.add(esmTemp);
				
			}
		}
		
		if(esmList.size() == 0) {
			esmList = null;
		}
		
		return esmList;
    }
   
    public List<EmployeeSkillModel> getAll() {
    	List<EmployeeSkillModel> list = new ArrayList<>();
    	employeeSkillRepository.findAll().forEach(list::add);
    	return list;
    }
    
    public List<EmployeeSkillModel> getAllByEmployeeSkilll(EmployeeSkill employeeSkill) {
    	List<EmployeeSkillModel> list = new ArrayList<>();
    	employeeSkillRepository.findByEmployeeSkill(employeeSkill).forEach(list::add);
    	return list;
    }


}
