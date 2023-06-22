package com.team13.WaitDoc.member.service;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private Member login(String username,String providerTypeCode){


        Member member = Member
                .builder()
                .name(username)
                .providerTypeCode(providerTypeCode)
                .memberRole(MemberRole.ROLE_USER)
                .phone("01011111111")
                .address("서울시")
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member whenSocialLogin(String username, String providerTypeCode) {

        return login(username,providerTypeCode);
    }
}
