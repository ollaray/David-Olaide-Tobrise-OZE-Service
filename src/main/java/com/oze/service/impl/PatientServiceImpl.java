package com.oze.service.impl;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oze.entity.Patient;
import com.oze.entity.Staff;
import com.oze.repository.PatientRepository;
import com.oze.repository.StaffRepository;
import com.oze.service.CSVService;
import com.oze.service.PatientService;
import com.oze.utils.CSVUtil;

@Service
@Transactional
public class PatientServiceImpl implements PatientService, CSVService{
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired 
	
	
	@Override
	public List<Patient> allPatients() {
		return patientRepository.findAll();
	}

	@Override
	public List<Patient> patientsByAge(long age) {
		return patientRepository.findByAgeGreaterThanEqual(age);
	}
	
	
	@Override
	public void deletePatientRecords(LocalDate dateFrom, LocalDate dateTo){
		patientRepository.deleteBylastVisitDateBetween(dateFrom, dateTo);
	}

	@Override
	public ByteArrayInputStream load() {
		List<Patient> patients = patientRepository.findAll();
		ByteArrayInputStream streamIn = CSVUtil.patientRecordsToCsv(patients);
		return streamIn;
	}
	
	

}
