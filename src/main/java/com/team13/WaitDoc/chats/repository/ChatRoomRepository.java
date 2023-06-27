package com.team13.WaitDoc.chats.repository;

import com.team13.WaitDoc.chats.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
