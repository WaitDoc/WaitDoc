package com.team13.WaitDoc.notification.repository;

import com.team13.WaitDoc.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	// 회원 아이디로 알림 리스트를 가져오는 메서드
	List<Notification> findByMemberIdOrderByCreateDateDesc(Long memberId);
}
