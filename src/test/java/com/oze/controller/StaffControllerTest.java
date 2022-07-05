package com.oze.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oze.entity.Staff;
import com.oze.repository.StaffRepository;
import com.oze.service.StaffService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StaffController.class)
class StaffControllerTest {
	
	@MockBean
	StaffService staffService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	StaffRepository staffRepository;
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	LocalDate date1 = LocalDate.parse("2019-10-10", formatter);
	LocalDate date2 = LocalDate.parse("2020-11-04",formatter);
	LocalDate date3 = LocalDate.parse("2021-12-02",formatter);
	
	
	//Caryn Eagle
	//Ilyse Curzon
	Staff staff1 = new Staff((long)(Math.random()*10), "Flory MacFayden", UUID.randomUUID(), date1);
	Staff staff2 = new Staff((long)(Math.random()*10), "Caryn Eagle", UUID.randomUUID(), date2);
	Staff staff3 = new Staff((long)(Math.random()*10), "Ilyse Curzon", UUID.randomUUID(), date3);
	
	@Test
	void testCreateStaff() throws Exception {
		Mockito.when(staffService.createStaff(staff1)).thenReturn(staff1);
		
		MockHttpServletRequestBuilder mockReq = MockMvcRequestBuilders.post("/api/v1/staff")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(staff1));
		
		mockMvc.perform(mockReq)
				.andExpect(status().isOk());
					
	}
	
	
	static String asJsonString(final Object obj) {
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());
        	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	


}


