package com.team13.WaitDoc.base.config.auth;

import com.team13.WaitDoc.member.entity.Member;
import lombok.Getter;

@Getter
public class SessionMember {

    private final String name;
    private final String email;
    private final Long memberId;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.memberId = member.getId();
    }
}
