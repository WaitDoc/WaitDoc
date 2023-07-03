package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Long> {
}
