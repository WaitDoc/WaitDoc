package com.team13.WaitDoc.waiting.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.waiting.entity.Waiting;
import com.team13.WaitDoc.waiting.repository.WaitingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitingService {
	private final WaitingRepository waitingRepository;

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final HospitalRepository hospitalRepository;
	private final MemberRepository memberRepository;

	public void send(String topic, String key, String value) {
		kafkaTemplate.send(topic, key, value);
	}


	//대기자 수 증가
	public void registerWaiting(Long hospitalId, Long memberId) {
		if(waitingRepository.existsByHospitalIdAndMemberId(hospitalId, memberId)) {
			throw new RuntimeException("이미 줄서기를 했습니다.");
		}
		Hospital hospital = hospitalRepository.findById(hospitalId)
			.orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다."));
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
		Waiting waiting = new Waiting(hospital, member);
		waitingRepository.save(waiting);
	}

	//대기자 수 감소
	public void cancelWaiting(Long hospitalId, Long memberId) {
		Waiting waiting = waitingRepository.findByHospitalIdAndMemberId(hospitalId, memberId);
		if (waiting != null) {
			waitingRepository.delete(waiting);
		}
	}

	//대기자 수 조회
	public int getWaitingCount(Long hospitalId) {
		return waitingRepository.countByHospitalId(hospitalId);
	}
}









