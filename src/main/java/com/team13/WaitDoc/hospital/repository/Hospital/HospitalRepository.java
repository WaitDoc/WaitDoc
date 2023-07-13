package com.team13.WaitDoc.hospital.repository.Hospital;

import com.team13.WaitDoc.hospital.entity.Hospital;

import com.team13.WaitDoc.hospital.repository.Hospital.HospitalDslRepository;
import com.team13.WaitDoc.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> , HospitalDslRepository {
    public Optional<Hospital> findByHpid(String hpid);


    Optional<Hospital> findByName(String name);

    Optional<Hospital> findById(Long id);




}
