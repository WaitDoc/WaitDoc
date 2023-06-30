package com.team13.WaitDoc.chats.service;

import com.team13.WaitDoc.chats.dto.InquiryMessageDto;
import com.team13.WaitDoc.chats.entity.InquiryMessage;
import com.team13.WaitDoc.chats.entity.InquiryMessageType;
import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import com.team13.WaitDoc.chats.repository.InquiryMessageRepository;
import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryMessageService {

    private final InquiryMessageRepository inquiryMessageRepository;
    private final HospitalInquiryService hospitalInquiryService;

    public InquiryMessage createAndSave(String content, Long senderId, Long chatRoomId, InquiryMessageType type) {

        HospitalInquiry hospitalInquiry = hospitalInquiryService.findByIdElseThrow(chatRoomId);

        HospitalInquiryMember sender = hospitalInquiry.getHospitalInquiryMembers().stream()
                .filter(hospitalInquiryMember -> hospitalInquiryMember.getMember().getId().equals(senderId))
                .findFirst()
                .orElseThrow();

        InquiryMessage inquiryMessage = InquiryMessage.builder()
                .content(content)
                .sender(sender)
                .type(type)
                .hospitalInquiry(hospitalInquiry)
                .build();

        return inquiryMessageRepository.save(inquiryMessage);
    }

    public List<InquiryMessageDto> getByChatRoomIdAndUserIdAndFromId(Long hospitalInquiryId, Long userId, Long fromId) {

        HospitalInquiry hospitalInquiry = hospitalInquiryService.findByIdElseThrow(hospitalInquiryId);

        hospitalInquiry.getHospitalInquiryMembers().stream()
                .filter(hospitalInquiryMember -> hospitalInquiryMember.getMember().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        List<InquiryMessage> inquiryMessages = inquiryMessageRepository.findByHospitalInquiryId(hospitalInquiryId);

        List<InquiryMessage> list = inquiryMessages.stream()
                .filter(inquiryMessage -> inquiryMessage.getId() > fromId)
                .sorted(Comparator.comparing(InquiryMessage::getId))
                .toList();

        return InquiryMessageDto.fromChatMessages(list);
    }
}
