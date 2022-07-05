package com.oze.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oze.entity.Patient;
import com.oze.service.CSVService;
import com.oze.service.PatientService;
import com.oze.service.StaffService;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
	
	@Autowired
	private CSVService csvService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private StaffService staffService;
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	@GetMapping(value = "/csv/download", produces="text/csv")
	public ResponseEntity<?> patientRecords(@RequestHeader("uuid") UUID uuid){
		boolean checkUUID = staffService.findStaff(uuid);
		if(checkUUID) {
			String filename = "patients.csv";
			
			InputStreamResource res = new InputStreamResource(csvService.load());
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
			headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
			
			return (ResponseEntity<?>)new ResponseEntity<>(res, headers, HttpStatus.OK);
		}
		
		return (ResponseEntity<?>)ResponseEntity.status(HttpStatus.UNAUTHORIZED);
		
	}
	
	@GetMapping("/patients/{age}")
	public ResponseEntity<?> fetchPatients(@PathVariable Long age, @RequestHeader("uuid") UUID uuid){
		boolean checkUUID = staffService.findStaff(uuid);
		if(checkUUID) {
			return ResponseEntity.ok().body(patientService.patientsByAge(age));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@DeleteMapping("/patients/{fromStr}/{toStr}")
	public ResponseEntity<?> deletePatients(@PathVariable String fromStr, @PathVariable String toStr, @RequestHeader("uuid") UUID uuid){
		LocalDate from = LocalDate.parse(fromStr, formatter);
		LocalDate to = LocalDate.parse(toStr, formatter);
		
		boolean checkUUID = staffService.findStaff(uuid);
		if(checkUUID) {
			patientService.deletePatientRecords(from, to);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}
		return (ResponseEntity<?>)ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	
}
