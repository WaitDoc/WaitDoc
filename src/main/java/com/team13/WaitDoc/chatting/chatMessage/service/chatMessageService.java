package com.team13.WaitDoc.chatting.chatMessage.service;

import com.team13.WaitDoc.chatting.chatMessage.dto.response.ChatMessageDto;
import com.team13.WaitDoc.chatting.chatMessage.entity.ChatMessage;
import com.team13.WaitDoc.chatting.chatMessage.entity.ChatMessageType;
import com.team13.WaitDoc.chatting.chatMessage.repository.ChatMessageRepository;
import com.team13.WaitDoc.chatting.chatRoom.entity.ChatRoom;
import com.team13.WaitDoc.chatting.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class chatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage createAndSave(String content, Long senderId, Long chatRoomId, ChatMessageType type) {

        ChatRoom chatRoom = chatRoomService.findById(chatRoomId);

        ChatMember sender = chatRoom.getChatMembers().stream()
                .filter(chatMember -> chatMember.getMember().getId().equals(senderId))
                .findFirst()
                .orElseThrow();

        ChatMessage chatMessage = ChatMessage.create(content, sender, type, chatRoom);

        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessageDto> getByChatRoomIdAndUserIdAndFromId(Long roomId, Long userId, Long fromId) {

        ChatRoom chatRoom = chatRoomService.findById(roomId);

        chatRoom.getChatMembers().stream()
                .filter(chatMember -> chatMember.getMember().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(roomId);

        List<ChatMessage> list = chatMessages.stream()
                .filter(chatMessage -> chatMessage.getId() > fromId)
                .sorted(Comparator.comparing(ChatMessage::getId))
                .toList();

        return ChatMessageDto.fromChatMessages(list);
    }
}
