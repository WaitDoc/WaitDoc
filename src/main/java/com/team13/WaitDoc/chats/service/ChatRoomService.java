package com.team13.WaitDoc.chats.service;


import com.team13.WaitDoc.chats.dto.ChatRoomDto;
import com.team13.WaitDoc.chats.entity.ChatRoom;
import com.team13.WaitDoc.chats.repository.ChatRoomRepository;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createAndSave(String name) {

        ChatRoom chatRoom = ChatRoom.create(name);

        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findById(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    public ChatRoomDto getByIdAndUserId(Long roomId, Long userId) {
        ChatRoom chatRoom = findById(roomId);

        chatRoom.getChatUsers().stream()
                .filter(chatUser -> chatUser.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        return ChatRoomDto.fromChatRoom(chatRoom);
    }

}
