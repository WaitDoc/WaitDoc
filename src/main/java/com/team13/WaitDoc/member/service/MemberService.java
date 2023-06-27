package com.team13.WaitDoc.member.service;

import com.team13.WaitDoc.base.config.auth.OAuthAttributes;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    //private final MemberRepository memberRepository;


    /*public Member findByIdElseThrow(Long userId) {
        return MemberRepository.findById(userId).orElseThrow();
    }

    /*public Member createAndSave(String username, String password) {
       Member user =Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return memberRepository.save(user);
    }

     */




    /*@Transactional
    public Member save(OAuthAttributes oAuthAttributes) {

        Member member = createMember(oAuthAttributes);
        memberRepository.save(member);

        return member;
    }

     */

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

