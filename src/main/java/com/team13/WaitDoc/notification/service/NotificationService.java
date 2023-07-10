package com.team13.WaitDoc.notification.service;

import org.springframework.stereotype.Service;

import com.team13.WaitDoc.notification.entity.Notification;
import com.team13.WaitDoc.notification.repository.NotificationRepository;

import java.util.List;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public List<Notification> getNotifications(Long memberId) {
		return notificationRepository.findByMemberIdOrderByCreateDateDesc(memberId);
	}

	public void readNotification(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid notification Id:" + notificationId));
		notification.setRead(true);
		notificationRepository.save(notification);
	}
}
