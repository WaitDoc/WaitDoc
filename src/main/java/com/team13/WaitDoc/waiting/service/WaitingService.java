package com.team13.WaitDoc.waiting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalRepository;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.waiting.dto.WaitingInfo;
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
		if (waitingRepository.existsByHospitalIdAndMemberId(hospitalId, memberId)) {
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

	//대기자 리스트 조회
	public List<WaitingInfo> getWaitingList(Long hospitalId) {
		List<Waiting> waitingList = waitingRepository.findByHospitalId(hospitalId);

		return waitingList.stream()
			.map(waiting -> {
				WaitingInfo info = new WaitingInfo(waiting.getMember().getId(), waiting.getMember().getName());
				info.setHospitalId(waiting.getHospital().getId());
				return info;
			})
			.collect(Collectors.toList());
	}

	public List<Hospital> getWaitingHospitalsByMemberId(Long memberId) {
		List<Waiting> waitings = waitingRepository.findByMemberId(memberId);
		return waitings.stream()
			.map(Waiting::getHospital)
			.collect(Collectors.toList());
	}

	//순서를 조회하는 메서드
	public int getMyWaitingOrder(Long hospitalId, Long memberId) {
		List<Waiting> waitingList = waitingRepository.findByHospitalIdOrderByCreateDateAsc(hospitalId);
		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).getMember().getId().equals(memberId)) {
				return i + 1;
			}
		}
		return 0;
	}

	@Scheduled(fixedDelay = 60000) // 60초(1분)마다 실행
	public void checkAndSendNotification() {
		List<Member> allMembers = memberRepository.findAll(); // 모든 회원 조회
		for (Member member : allMembers) {
			List<Long> hospitalIds = member.getHospitalIds(); // 해당 멤버가 대기 중인 모든 병원의 ID를 가져옵니다.
			for (Long hospitalId : hospitalIds) {
				int waitingOrder = getMyWaitingOrder(hospitalId, member.getId());
				if (waitingOrder == 1 || waitingOrder == 5) {
					sendNotification(member, hospitalId); // 조건에 맞으면 알림 보내기
				}
			}
		}
	}

	public void sendNotification(Member member, Long hospitalId) {
		// 1. 병원 정보 가져오기
		Hospital hospital = hospitalRepository.findById(hospitalId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 병원 정보를 찾을 수 없습니다. id=" + hospitalId));

		// 2. 알림 메시지 생성
		String message =  member.getName() + "님, " +
			"현재 " + hospital.getName() + "의 대기 순서가 근접해오고 있습니다. 준비해 주세요.";

		// 3. 알림 보내기 (여기서는 예시로 System.out.println을 사용하였습니다.)
		// 이 부분은 실제 알림 서비스(이메일, SMS, 푸시 알림 등)를 사용하여 메시지를 보내는 코드로 변경해야 합니다.
		System.out.println(message);
	}

}






