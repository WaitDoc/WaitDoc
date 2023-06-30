package com.team13.WaitDoc.chats.service;

import com.team13.WaitDoc.chats.dto.ChatMessageDto;
import com.team13.WaitDoc.chats.entity.InquiryMessage;
import com.team13.WaitDoc.chats.entity.ChatMessageType;
import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import com.team13.WaitDoc.chats.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public InquiryMessage createAndSave(String content, Long senderId, Long chatRoomId, ChatMessageType type) {

        HospitalInquiry hospitalInquiry = chatRoomService.findById(chatRoomId);

        HospitalInquiryMember sender = hospitalInquiry.getHospitalInquiryMembers().stream()
                .filter(hospitalInquiryMember -> hospitalInquiryMember.getUser().getId().equals(senderId))
                .findFirst()
                .orElseThrow();

        InquiryMessage inquiryMessage = InquiryMessage.builder()
                .content(content)
                .sender(sender)
                .type(type)
                .hospitalInquiry(hospitalInquiry)
                .build();

        return chatMessageRepository.save(inquiryMessage);
    }

    public List<ChatMessageDto> getByChatRoomIdAndUserIdAndFromId(Long roomId, Long userId, Long fromId) {

        HospitalInquiry hospitalInquiry = chatRoomService.findById(roomId);

        hospitalInquiry.getHospitalInquiryMembers().stream()
                .filter(hospitalInquiryMember -> hospitalInquiryMember.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        List<InquiryMessage> inquiryMessages = chatMessageRepository.findByChatRoomId(roomId);

        List<InquiryMessage> list = inquiryMessages.stream()
                .filter(inquiryMessage -> inquiryMessage.getId() > fromId)
                .sorted(Comparator.comparing(InquiryMessage::getId))
                .toList();

        return ChatMessageDto.fromChatMessages(list);
    }
}
