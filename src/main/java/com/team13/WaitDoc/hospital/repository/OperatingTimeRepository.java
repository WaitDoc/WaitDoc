package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface OperatingTimeRepository extends JpaRepository<OperatingTime, Long> {
    Optional<OperatingTime> findByHospital(Hospital hospital);
}
