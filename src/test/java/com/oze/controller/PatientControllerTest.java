package com.oze.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oze.repository.PatientRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PatientControllerTest {
	@Autowired
	PatientController patientController;
	
	//Test to Check if Context is set up correctly
	@Test
	public void contextLoads() {
	    Assertions.assertThat(patientController).isNotNull();
	}
	
	@Test
	public void contextLoads(ApplicationContext context) {
	    assertThat(context).isNotNull();
	}
	
	@Test
	void testIfPatientRepositoryIsConfigured(ApplicationContext context) {
	  assertThat(context.getBean(PatientRepository.class)).isNotNull();
	}

}
