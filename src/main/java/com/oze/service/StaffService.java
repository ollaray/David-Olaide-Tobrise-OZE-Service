package com.oze.service;

import java.util.UUID;

import com.oze.entity.Staff;

public interface StaffService {
	Staff createStaff(Staff staff);
	Staff updateStaff(Staff staff);
	boolean findStaff(UUID uuid);
	Staff getStaff(long id);
	Staff findById(Long id);
}
