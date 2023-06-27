package com.team13.WaitDoc.chats.repository;


import com.team13.WaitDoc.chats.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomId(Long roomId);
}

