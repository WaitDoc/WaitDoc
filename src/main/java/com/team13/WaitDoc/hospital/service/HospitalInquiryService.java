package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.chats.entity.ChatRoom;
import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import com.team13.WaitDoc.hospital.repository.HospitalInquiryRepository;
import com.team13.WaitDoc.chats.service.ChatRoomService;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalInquiryService {

    private final ChatRoomService chatRoomService;
    private final HospitalService hospitalService;
    private final MemberService memberService;
    private final HospitalInquiryRepository hospitalChatRoomRepository;

    public void inquiry(Long hospitalId, Long memberId) {
        ChatRoom chatRoom = createHospitalRoom(hospitalId);

        //Member member = memberService.findByIdElseThrow(memberId);
        //chatRoom.addChatUser(member);
    }

    private ChatRoom createHospitalRoom(Long hospitalId) {
        Hospital hospital = hospitalService.findByIdElseThrow(hospitalId);
        ChatRoom chatRoom = chatRoomService.createAndSave(hospital.getName());
        createAndSave(hospital.getId(), chatRoom.getId());
        return chatRoom;
    }

    public HospitalInquiry createAndSave(Long hospitalId, Long roomId) {

        Hospital hospital = hospitalService.findByIdElseThrow(hospitalId);
        ChatRoom chatRoom = chatRoomService.findById(roomId);

        HospitalInquiry hospitalChatRoom = HospitalInquiry.builder()
                .room(chatRoom)
                .hospital(hospital)
                .build();

        return hospitalChatRoomRepository.save(hospitalChatRoom);
    }

    public HospitalInquiry findByIdElseThrow(Long hospitalId) {
        return hospitalChatRoomRepository.findById(hospitalId)
                .orElseThrow();
    }

    public HospitalInquiry enterHospitalChatRoom(Long hospitalRoomId, Long memberId) {
        HospitalInquiry hospitalChatRoom = findByIdElseThrow(hospitalRoomId);

        ChatRoom chatRoom = hospitalChatRoom.getRoom();

        chatRoom.getChatUsers().stream()
                .filter(chatUser -> chatUser.getUser().getId().equals(memberId))
                .findFirst()
                .orElseThrow();

        return hospitalChatRoom;
    }
}
