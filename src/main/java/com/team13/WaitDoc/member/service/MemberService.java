package com.team13.WaitDoc.member.service;


import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;


    public Member findByIdElseThrow(Long userId) {
        return MemberRepository.findById(userId).orElseThrow();
    }

    public Member createAndSave(String username, String password) {
       Member user =Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return memberRepository.save(user);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    }
