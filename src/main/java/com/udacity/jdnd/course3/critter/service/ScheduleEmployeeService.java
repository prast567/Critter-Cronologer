package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleEmployee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleEmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulePetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleSkillRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

@Component
@Transactional 
public class ScheduleEmployeeService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleSkillRepository scheduleSkillRepository;
	
	@Autowired
	SchedulePetRepository schedulePetRepository;
	
	@Autowired
	ScheduleEmployeeRepository scheduleEmployeeRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Set<ScheduleEmployee> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {
		
		Set<ScheduleEmployee> empList = new HashSet<ScheduleEmployee>();
		
		if(null != scheduleDTO.getEmployeeIds()) {
			for(Long empIds : scheduleDTO.getEmployeeIds()) {
				
				Employee emp = employeeRepository.findById(empIds.longValue());
				
				ScheduleEmployee schedEmp = new ScheduleEmployee();
				schedEmp.setEmployee(emp);
				schedEmp.setSchedule(schedule);
				
				scheduleEmployeeRepository.save(schedEmp);
				
				empList.add(schedEmp);
				
			}
		}
		
		if(empList.size() == 0) {
			empList = null;
		}
		
		return empList;
    }
	
	public List<Long> getEmployeeIdsByScheduleId(Long scheduleId){
		
		Schedule schedule = scheduleRepository.findById(scheduleId.longValue());
		
		List<ScheduleEmployee> empList = scheduleEmployeeRepository.findScheduleEmployeesBySchedule(schedule);
		List<Long> empIds = new ArrayList<Long>();
		
		for(ScheduleEmployee esm : empList) {
			
			empIds.add(esm.getEmployee().getId());
			
		}
		
		return empIds;
		
	}
	
	public List<ScheduleEmployee> getScheduleForEmployee(long empId){
		
		Employee employee = employeeRepository.findById(empId);

		return scheduleEmployeeRepository.findScheduleEmployeesByEmployee(employee);
		
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
