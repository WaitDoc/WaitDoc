package com.team13.WaitDoc.member.service;

import com.team13.WaitDoc.base.config.auth.OAuthAttributes;
import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.security.SecurityUser;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElse(null);
    }

    public Member findByIdElseThrow(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    } //이게 맞나?

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("No member found with ID: " + memberId));
    }

    public String findNameById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
        return member.getName();
    }


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

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member with id " + memberId + " not found"));
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Member with name " + name + " not found"));
    }



    @Transactional
    public void setRoleAsAdmin(Long memberId, String adminKey) {

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        if (adminKey.equals("1122334455")) {
            member.setMemberRole(MemberRole.ROLE_ADMIN);
        } else {
            throw new IllegalArgumentException("Invalid admin key");
        }

        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Member member) {
        Member existingMember = memberRepository.findById(member.getId())
            .orElseThrow(() -> new IllegalArgumentException("Member does not exist with the id: " + member.getId()));

        existingMember.setName(member.getName());
        existingMember.setEmail(member.getEmail());

        memberRepository.save(existingMember);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
    }

    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}



