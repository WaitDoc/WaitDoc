package com.team13.WaitDoc.chatting.chatMessage.dto.request;

import com.team13.WaitDoc.chatting.chatMessage.entity.ChatMessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageRequest {
    private String content;
    private ChatMessageType type;
}
