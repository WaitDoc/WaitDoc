package com.team13.WaitDoc.member.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.util.Assert;

import lombok.experimental.SuperBuilder;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String email;
    private String gender;
    private String birthday;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    public Member(String username, String password) {

        Assert.notNull(username, "username는 널일 수 없습니다.");
        Assert.notNull(password, "password는 널일 수 없습니다.");

        this.name = username;
        //this.password = password;
    }

    public Member(String name, String email, String gender, String birthday, MemberRole memberRole) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.memberRole = memberRole;
    }

}
