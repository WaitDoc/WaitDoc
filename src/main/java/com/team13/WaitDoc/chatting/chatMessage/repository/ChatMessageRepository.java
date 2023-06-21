package com.team13.WaitDoc.chatting.chatMessage.repository;

import com.team13.WaitDoc.chatting.chatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomId(Long roomId);
}
