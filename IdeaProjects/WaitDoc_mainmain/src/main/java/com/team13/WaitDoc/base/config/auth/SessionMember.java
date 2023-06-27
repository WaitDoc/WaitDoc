package com.team13.WaitDoc.base.config.auth;

import com.team13.WaitDoc.member.entity.Member;
import lombok.Getter;

@Getter
public class SessionMember {

    private final String nickname;
    private final String email;

    public SessionMember(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
