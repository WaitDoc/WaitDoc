package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> findByName(String name);

}
