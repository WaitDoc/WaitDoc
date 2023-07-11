package com.team13.WaitDoc.notification.repository;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByMemberIdOrderByCreateDateDesc(Long memberId);
}
