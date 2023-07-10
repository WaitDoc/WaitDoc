package com.team13.WaitDoc.notification.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team13.WaitDoc.hospital.dto.HospitalResponseDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalRepository;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.notification.entity.Notification;
import com.team13.WaitDoc.notification.repository.NotificationRepository;
import com.team13.WaitDoc.waiting.service.WaitingService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
// @AllArgsConstructor
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
	private final MemberRepository memberRepository;

	private final HospitalRepository hospitalRepository;

	private final WaitingService waitingService;


	public List<Notification> getNotifications(Long memberId) {
		return notificationRepository.findByMemberIdOrderByCreateDateDesc(memberId);
	}

	public void readNotification(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid notification Id:" + notificationId));
		notification.setRead(true);
		notificationRepository.save(notification);
	}

	@Transactional
	@Scheduled(fixedDelay = 180000) // 3분마다 실행
	public void checkAndSendNotification() {
		List<Member> allMembers = memberRepository.findAll(); // 모든 회원 조회
		for (Member member : allMembers) {
			List<Long> hospitalIds = member.getHospitalIds(); // 해당 멤버가 대기 중인 모든 병원의 ID를 가져옵니다.
			for (Long hospitalId : hospitalIds) {
				int waitingOrder = waitingService.getMyWaitingOrder(hospitalId, member.getId());
				if (waitingOrder == 1) {
					sendNotification(member, hospitalId, 1); // 1번째일때 알림 보내기
				}
				if (waitingOrder == 5) {
					sendNotification(member, hospitalId, 5); // 5번째일때 알림 보내기
				}
			}
		}
	}


	public void sendNotification(Member member, Long hospitalId, int waitingOrder) {
		// 1. 병원 정보 가져오기
		Hospital hospital = hospitalRepository.findById(hospitalId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 병원 정보를 찾을 수 없습니다. id=" + hospitalId));

		// 2. 알림 메시지 생성
		String message = member.getName() + "님, " +
			"현재 " + hospital.getName() + "의 대기 순서가 " + waitingOrder + "번째입니다. 준비해 주세요.";

		// 3. 알림 객체 생성 및 저장
		Notification notification = new Notification();
		notification.setMember(member);
		notification.setContent(message);
		notification.setRead(false);
		notification.setCreateDate(LocalDateTime.now());
		notificationRepository.save(notification);
	}


}
