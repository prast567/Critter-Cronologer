package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeDayAvailable;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.repository.EmployeeCustomRespository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import org.springframework.stereotype.Service;

@Service
@Component
@Transactional 
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeSkillRepository employeeSkillRepository;
	
	@Autowired
	EmployeeSkillService employeeSkillService;
	
	@Autowired
    EmployeeDayAvailableService employeeDayAvailableService;
	
	@Autowired
	EmployeeCustomRespository employeeCustomRepository;
	
	
	public EmployeeDTO add(EmployeeDTO dto) {
		
		Employee employee = new Employee();
		employee.setName(dto.getName());
		
		employee = employeeRepository.save(employee);
		
		Set<EmployeeSkillModel> esmList = employeeSkillService
												.addMultiple(dto, employee);
		
		employee.setSkills(esmList);
		Set<EmployeeDayAvailable> edaList = employeeDayAvailableService
												.addMultiple(dto, employee);
		
		employee.setDaysAvailable(edaList);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		return toDTO(savedEmployee);
    }
   
    public List<EmployeeDTO> getAll() {
    	
    	Iterator<Employee> allEmployeeList = employeeRepository.findAll().iterator();
    	List<EmployeeDTO> returnList = new ArrayList<EmployeeDTO>();
    	
    	while(allEmployeeList.hasNext()) {
    		
    		Employee tmpEmployee = allEmployeeList.next();

    		returnList.add(toDTO(tmpEmployee));

    	}
    	
    	return returnList;
    }
    
    public EmployeeDTO findById(long id){
    	Employee emp = employeeRepository.findById(id);
    	return toDTO(emp);
    }
    
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, long employeeId) {
    	
    	Employee emp = employeeRepository.findById(employeeId);
    	EmployeeDTO edto = toDTO(emp);
    	edto.setDaysAvailable(daysAvailable);
    	
    	Set<EmployeeDayAvailable> edaList = employeeDayAvailableService
				.addMultiple(edto, emp);
    	
    	emp.setDaysAvailable(edaList);
    	
    	employeeRepository.save(emp);
    	
    }
    
    public List<EmployeeDTO> getAllEmployeesForRequest( EmployeeRequestDTO employeeDTO){
    	
    	List<List<EmployeeDTO>> requestList = new ArrayList<List<EmployeeDTO>>();
    	
    	Iterator<EmployeeSkill> empsk =  employeeDTO.getSkills().iterator();
    	
    	while(empsk.hasNext()) {
    		
    		List<EmployeeDTO> allList = new ArrayList<EmployeeDTO>();
    		
    		EmployeeSkill tempempsk = empsk.next();
    		
    		
    		for(EmployeeSkillModel esm : employeeSkillService.getAllByEmployeeSkilll(tempempsk)) {
    			
    			allList.add(toDTO(esm.getEmployee()));
    			
    		}
    		
    		requestList.add(allList);
    		
    	}
    	
    	//CollectionUtils.

    	return null;
    }
    
    
    public List<EmployeeDTO> getAllByEmployeeSkillWhere(EmployeeRequestDTO employeeReqDTO){
    
    	
    	Iterator<EmployeeSkill> empsk =  employeeReqDTO.getSkills().iterator();
    	List<EmployeeDTO> allList = new ArrayList<EmployeeDTO>();
    	
    	ArrayList<Long> empskillmode = new ArrayList<Long>();

    	while(empsk.hasNext()) {
    		
    		EmployeeSkill tempempsk = empsk.next();
    		
    		empskillmode.add(Long.valueOf(tempempsk.ordinal()));
    		
    	}

    	ArrayList<Long> empList = employeeSkillRepository.findEmployeeSkillModel(empskillmode, empskillmode.size());
    	
    	Set<Employee> emList = new HashSet<Employee>();
    	
    	for(Long empid: empList) {
    		
    		emList.add(employeeRepository.findById(empid.longValue()));
    		
    	}
    	
    	Iterator<Employee> emIterator = emList.iterator();
    	
    	while(emIterator.hasNext()) {
    		
    		Employee tmpEmp = emIterator.next();
    		allList.add(toDTO(tmpEmp));
    		
    	}
    	
    	return allList;
    	
    }

    private Employee toEntity(EmployeeDTO dto) {
    	Employee entity = new Employee();
        entity.setName(dto.getName());
        
        
        
        return entity;
    }
    
    private EmployeeDTO toDTO(Employee entity) {
    	EmployeeDTO dto = new EmployeeDTO();
    	dto.setId(entity.getId());
    	dto.setName(entity.getName());
    	
    	dto.setDaysAvailable(entity.getDaysAvailableSetOnly());
    	dto.setSkills(entity.getEmployeeSkillsSetOnly());
    	
        return dto;
    }
}
