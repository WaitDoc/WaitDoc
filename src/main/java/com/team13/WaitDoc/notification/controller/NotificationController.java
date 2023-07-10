package com.team13.WaitDoc.notification.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.notification.entity.Notification;
import com.team13.WaitDoc.notification.service.NotificationService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;


	@GetMapping("/notifications")
	public String getNotifications(Model model, HttpSession session) {
		SessionMember sessionMember = (SessionMember) session.getAttribute("member");

		if (sessionMember != null) {
			Long memberId = sessionMember.getMemberId();
			List<Notification> notifications = notificationService.getNotifications(memberId);
			model.addAttribute("notifications", notifications);
		}

		return "notification/notifications";
	}

	@PostMapping("/notifications/{id}/read")
	public String readNotification(@PathVariable Long id) {
		notificationService.readNotification(id);
		return "redirect:/notifications";
	}
}
