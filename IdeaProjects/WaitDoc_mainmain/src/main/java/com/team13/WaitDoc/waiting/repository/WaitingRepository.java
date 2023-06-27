package com.team13.WaitDoc.waiting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team13.WaitDoc.waiting.entity.Waiting;

@Repository
public interface WaitingRepository extends JpaRepository<Waiting, Long> {
	int countByHospitalId(Long hospitalId);
	boolean existsByHospitalIdAndMemberId(Long hospitalId, Long memberId);
	Waiting findByHospitalIdAndMemberId(Long hospitalId, Long memberId);

}