package com.team13.WaitDoc.chats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = LAZY)
    private ChatUser sender;

    @ManyToOne(fetch = LAZY)
    private ChatRoom chatRoom;

    @Enumerated(STRING)
    private ChatMessageType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public ChatMessage(String content, ChatUser sender, ChatRoom chatRoom, ChatMessageType type) {

        Assert.notNull(content, "content는 널일 수 없습니다.");
        Assert.notNull(sender, "sender는 널일 수 없습니다.");
        Assert.notNull(chatRoom, "chatRoom는 널일 수 없습니다.");

        this.content = content;
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
