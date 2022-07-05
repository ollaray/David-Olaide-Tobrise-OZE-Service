package com.oze.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oze.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findByAgeGreaterThanEqual(long age);
	void deleteBylastVisitDateBetween(LocalDate from, LocalDate to );
}
