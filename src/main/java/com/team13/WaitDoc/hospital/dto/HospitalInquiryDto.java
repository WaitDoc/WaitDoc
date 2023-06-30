package com.team13.WaitDoc.chats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    @JsonProperty("chat_room_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static ChatRoomDto fromChatRoom(HospitalInquiry hospitalInquiry) {

        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                .id(hospitalInquiry.getId())
                .name(hospitalInquiry.getName())
                .createdAt(hospitalInquiry.getCreatedAt())
                .updatedAt(hospitalInquiry.getUpdatedAt())
                .build();

        return chatRoomDto;
    }
}
