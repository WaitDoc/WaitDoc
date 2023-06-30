package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalInquiryRepository extends JpaRepository<HospitalInquiry, Long> {
    List<HospitalInquiry> findAllByHospitalId(Long hospitalId);
}
