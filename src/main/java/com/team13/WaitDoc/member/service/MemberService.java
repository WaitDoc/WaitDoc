package com.team13.WaitDoc.member.service;

import com.team13.WaitDoc.base.config.auth.OAuthAttributes;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Member findByIdElseThrow(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    } //이게 맞나?






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

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member with id " + memberId + " not found"));
    }


}

