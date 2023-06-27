package com.team13.WaitDoc.member.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(nullable = false)
    private String nickname;
    private String email;
    private String gender;
    private String birthday;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    public Member(String nickname, String email, String gender, String birthday, MemberRole memberRole) {
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.memberRole = memberRole;
    }
}
