package com.team13.WaitDoc.hospital.Repository;

import com.team13.WaitDoc.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
