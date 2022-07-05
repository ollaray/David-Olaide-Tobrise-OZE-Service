package com.oze.service;

import java.time.LocalDate;
import java.util.List;

import com.oze.entity.Patient;

public interface PatientService {
	List<Patient> allPatients();
	List<Patient> patientsByAge(long age);
	void deletePatientRecords(LocalDate dateFrom, LocalDate dateTo);
	
}
