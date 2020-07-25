package com.udacity.jdnd.course3.critter.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleEmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulePetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleSkillRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

@Component
@Transactional 
public class ScheduleSkillService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleSkillRepository scheduleSkillRepository;
	
	@Autowired
	SchedulePetRepository schedulePetRepository;
	
	@Autowired
	ScheduleEmployeeRepository scheduleEmployeeRepository;
	
	@Autowired
	EmployeeSkillRepository employeeSkillRepository;

	public Set<ScheduleSkill> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {
		
		Set<ScheduleSkill> allList = new HashSet<ScheduleSkill>();
		
		if(null != scheduleDTO.getActivities()) {
			for(EmployeeSkill skill : scheduleDTO.getActivities()) {
				
				ScheduleSkill schedSkill = new ScheduleSkill();
				schedSkill.setEmployeeSkill(skill);
				schedSkill.setSchedule(schedule);
				
				scheduleSkillRepository.save(schedSkill);
				
				allList.add(schedSkill);
				
			}
		}
		
		if(allList.size() == 0) {
			allList = null;
		}
		
		return allList;
    }
	
	public Set<EmployeeSkill> getSkillByScheduleId(Long scheduleId){
		
		Schedule schedule = scheduleRepository.findById(scheduleId.longValue());
		
		List<ScheduleSkill> empList = scheduleSkillRepository.findScheduleSkillsBySchedule(schedule);
		Set<EmployeeSkill> empSkill = new HashSet<EmployeeSkill>();
		
		for(ScheduleSkill esm : empList) {
			
			empSkill.add(esm.getEmployeeSkill());
			
		}
		
		return empSkill;
		
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
