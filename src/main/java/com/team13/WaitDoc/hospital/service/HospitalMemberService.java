package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.HospitalMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalMemberService {

    private final HospitalMemberRepository hospitalMemberRepository;

    public HospitalMember findByMemberIdElseThrow(Long memberId) {
        return hospitalMemberRepository.findByMemberId(memberId)
                .orElseThrow();
    }
}
