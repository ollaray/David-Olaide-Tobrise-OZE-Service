package com.oze.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oze.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	Staff findByUuid(UUID uuid);
}
