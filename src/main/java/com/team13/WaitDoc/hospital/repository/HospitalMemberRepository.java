package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.HospitalMember;
import com.team13.WaitDoc.hospital.entity.HospitalMemberRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalMemberRepository extends JpaRepository<HospitalMember, Long> {
    Optional<HospitalMember> findByMemberId(Long memberId);

    List<HospitalMember> findAllByRole(HospitalMemberRole role);
}
