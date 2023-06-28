package com.team13.WaitDoc.chats.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chatRoom", cascade = PERSIST)
    @Builder.Default
    private Set<ChatUser> chatUsers = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isActive;

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .name(name)
                .isActive(true)
                .build();
    }

    public void addChatUser(Member member) {
        ChatUser chatUser = ChatUser.builder()
                .user(member)
                .chatRoom(this)
                .build();
        chatUsers.add(chatUser);
    }
}
