package com.oze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oze.entity.Staff;
import com.oze.service.StaffService;

@RestController
@RequestMapping("/api/v1")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@PostMapping("/staff")         
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
		return ResponseEntity.ok().body(staffService.createStaff(staff));
	}
	
	@PutMapping("/staff/{id}")
	public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff, @PathVariable Long id){
		if(staffService.findById(id) != null) {
			staff.setId(id);
			return ResponseEntity.ok().body(staffService.updateStaff(staff));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> getStaff(@PathVariable Long id){
		return ResponseEntity.ok().body(staffService.getStaff(id));
	}
}
