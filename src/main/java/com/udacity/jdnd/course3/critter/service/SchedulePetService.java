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
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.SchedulePet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleEmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulePetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleSkillRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

@Component
@Transactional 
public class SchedulePetService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleSkillRepository scheduleSkillRepository;
	
	@Autowired
	SchedulePetRepository schedulePetRepository;
	
	@Autowired
	ScheduleEmployeeRepository scheduleEmployeeRepository;
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Set<SchedulePet> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {
		
		Set<SchedulePet> allList = new HashSet<SchedulePet>();
		
		if(null != scheduleDTO.getPetIds()) {
			for(Long petId : scheduleDTO.getPetIds()) {
				
				Pet pet = petRepository.findById(petId.longValue());
				
				SchedulePet schedPet = new SchedulePet();
				schedPet.setPet(pet);
				schedPet.setSchedule(schedule);
				
				schedulePetRepository.save(schedPet);
				
				allList.add(schedPet);
				
			}
		}
		
		if(allList.size() == 0) {
			allList = null;
		}
		
		return allList;
    }
	
	public List<Long> getPetIdsByScheduleId(Long scheduleId){
		
		Schedule schedule = scheduleRepository.findById(scheduleId.longValue());
		
		List<SchedulePet> petList = schedulePetRepository.findSchedulePetsBySchedule(schedule);
		List<Long> petIds = new ArrayList<Long>();
		
		for(SchedulePet esm : petList) {
			
			petIds.add(esm.getPet().getId());
			
		}
		
		return petIds;
		
	}

	public List<SchedulePet> getScheduleForPet(long petId){
		
		Pet pet = petRepository.findById(petId);

		return schedulePetRepository.findSchedulePetsByPet(pet);
		
	}
	
	public List<SchedulePet> getScheduleForOwner(long customerId){
		
		List<Long> schedPetIds = schedulePetRepository.findSchedulePetsByCustomerId(customerId);
		
		List<SchedulePet> schedLists = new ArrayList<SchedulePet>();
		
		for(Long id : schedPetIds) {
			schedLists.add(schedulePetRepository.findById(id.longValue()));
		}
		
		return schedLists;
		
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
