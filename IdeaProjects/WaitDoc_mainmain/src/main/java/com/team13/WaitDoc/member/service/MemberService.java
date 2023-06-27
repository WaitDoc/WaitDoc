package com.team13.WaitDoc.member.service;

import com.team13.WaitDoc.base.config.auth.OAuthAttributes;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member save(OAuthAttributes oAuthAttributes) {

        Member member = createMember(oAuthAttributes);
        memberRepository.save(member);

        return member;
    }

    private Member createMember(OAuthAttributes oAuthAttributes) {
        return new Member(
                oAuthAttributes.getNickname(),
                oAuthAttributes.getEmail(),
                oAuthAttributes.getGender(),
                oAuthAttributes.getBirthday(),
                MemberRole.ROLE_USER
        );
    }
}
