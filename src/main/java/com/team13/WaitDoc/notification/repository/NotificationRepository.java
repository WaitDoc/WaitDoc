package com.team13.WaitDoc.notification.repository;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByMemberIdOrderByCreateDateDesc(Long memberId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Notification n WHERE n.createDate < :date")
	void deleteByCreateDateBefore(@Param("date") LocalDateTime date);
}
