package com.team13.WaitDoc.notification.service;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalRepository;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.notification.entity.Notification;
import com.team13.WaitDoc.notification.event.NotificationEvent;
import com.team13.WaitDoc.notification.repository.NotificationRepository;
import com.team13.WaitDoc.waiting.entity.Waiting;
import com.team13.WaitDoc.waiting.service.WaitingService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
	private final HospitalRepository hospitalRepository;
	private final WaitingService waitingService;

	public List<Notification> getNotifications(Long memberId) {
		return notificationRepository.findByMemberIdOrderByCreateDateDesc(memberId);
	}


	@Async
	@EventListener
	public void handleNotificationEvent(NotificationEvent event) {
		Member member = event.getMember();
		Hospital hospital = event.getHospital();
		Waiting waiting = event.getWaiting();
		int waitingOrder = waitingService.getMyWaitingOrder(hospital.getId(), member.getId());
		if (waitingOrder == 1 || waitingOrder == 5) {
			sendNotification(member, hospital.getId(), waitingOrder);
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

	@Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
	public void deleteExpiredNotifications() {
		LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
		notificationRepository.deleteByCreateDateBefore(yesterday);
	}


}

