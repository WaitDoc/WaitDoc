package com.team13.WaitDoc.chats.repository;


import com.team13.WaitDoc.chats.entity.InquiryMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<InquiryMessage, Long> {
    List<InquiryMessage> findByChatRoomId(Long roomId);
}

