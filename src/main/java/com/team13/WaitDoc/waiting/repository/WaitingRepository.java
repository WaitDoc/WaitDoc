package com.team13.WaitDoc.waiting.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team13.WaitDoc.waiting.entity.Waiting;

@Repository
public interface WaitingRepository extends JpaRepository<Waiting, Long> {
	int countByHospitalId(Long hospitalId);
	boolean existsByHospitalIdAndMemberId(Long hospitalId, Long memberId);
	Waiting findByHospitalIdAndMemberId(Long hospitalId, Long memberId);
	List<Waiting> findByHospitalId(Long hospitalId);
	List<Waiting> findByMemberId(Long memberId);
	List<Waiting> findByHospitalIdOrderByCreateDateAsc(Long hospitalId);

	@Modifying
	@Transactional
	void deleteByCreateDateBefore(LocalDateTime date);
}