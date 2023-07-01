package com.team13.WaitDoc.chats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team13.WaitDoc.chats.entity.InquiryMessage;
import com.team13.WaitDoc.chats.entity.InquiryMessageType;
import com.team13.WaitDoc.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryMessageDto {

    @JsonProperty("message_id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("sender")
    private MemberDto sender;

    @JsonProperty("type")
    private InquiryMessageType type;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static InquiryMessageDto fromChatMessage(InquiryMessage inquiryMessage) {

        MemberDto userDto = MemberDto.fromUser(inquiryMessage.getSender().getMember()); //member?

        InquiryMessageDto inquiryMessageDto = InquiryMessageDto.builder()
                .id(inquiryMessage.getId())
                .type(inquiryMessage.getType())
                .sender(userDto)
                .content(inquiryMessage.getContent())
                .type(inquiryMessage.getType())
                .createdAt(inquiryMessage.getCreatedAt())
                .updatedAt(inquiryMessage.getUpdatedAt())
                .build();

        return inquiryMessageDto;
    }

    public static List<InquiryMessageDto> fromChatMessages(List<InquiryMessage> messages) {
        return messages.stream()
                .map(InquiryMessageDto::fromChatMessage)
                .toList();
    }
}
