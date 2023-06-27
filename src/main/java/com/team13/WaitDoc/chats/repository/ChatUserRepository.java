package com.team13.WaitDoc.chats.repository;

import com.team13.WaitDoc.chats.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
