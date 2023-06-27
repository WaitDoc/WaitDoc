package com.team13.WaitDoc.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team13.WaitDoc.hospital.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
