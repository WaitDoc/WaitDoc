package com.team13.WaitDoc.chats.entity;

import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
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
    private HospitalInquiry hospitalInquiry;

    @Enumerated(STRING)
    private ChatMessageType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public ChatMessage(String content, ChatUser sender, HospitalInquiry hospitalInquiry, ChatMessageType type) {

        Assert.notNull(content, "content는 널일 수 없습니다.");
        Assert.notNull(sender, "sender는 널일 수 없습니다.");
        Assert.notNull(hospitalInquiry, "chatRoom는 널일 수 없습니다.");

        this.content = content;
        this.sender = sender;
        this.hospitalInquiry = hospitalInquiry;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
