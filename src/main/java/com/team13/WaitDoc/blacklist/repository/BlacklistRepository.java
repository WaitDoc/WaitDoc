package com.team13.WaitDoc.blacklist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team13.WaitDoc.blacklist.entity.Blacklist;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

	Optional<Blacklist> findByHospitalAndMember(Hospital hospital, Member member);

	Optional<Blacklist> findByMemberId(Long memberId);


}
