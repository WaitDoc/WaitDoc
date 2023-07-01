package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.HospitalMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalMemberRepository extends JpaRepository<HospitalMember, Long> {
    Optional<HospitalMember> findByMemberId(Long memberId);
}
