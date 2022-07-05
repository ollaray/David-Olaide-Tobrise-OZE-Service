package com.oze.service.impl;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oze.entity.Staff;
import com.oze.exceptions.ResourceNotFoundException;
import com.oze.repository.StaffRepository;
import com.oze.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public Staff updateStaff(Staff staff) {
		Optional<Staff> row = staffRepository.findById(staff.getId());
		if(row.isPresent()) {
			Staff updateStaff = row.get();
			updateStaff.setId(staff.getId());
			updateStaff.setName(staff.getName());
			updateStaff.setRegistrationDate(staff.getRegistrationDate());
			updateStaff.setUuid(staff.getUuid());
			staffRepository.save(updateStaff);
			return updateStaff;
			
		}else {
			throw new ResourceNotFoundException("Resource with the id: "+staff.getId()+" was not found");
		}
		
		
	}
	
	public Staff findById(Long id) {
		Staff staff = staffRepository.findById(id).get();
		if(staff != null) {
			return staff;
		}
			return null;
		
	}
	
	@Override
	public Staff getStaff(long id) {
		Staff staff = staffRepository.getReferenceById(id);
		if(staff != null) {
			return staff;
		}else {
			throw new ResourceNotFoundException("Resource with the id: "+id+" was not found");
		}
		
	}

	@Override
	public boolean findStaff(UUID uuid) {
		Staff staff = staffRepository.findByUuid(uuid);
		if(staff != null) {
			return true;
		}
		return false;
	}
	
	

}
