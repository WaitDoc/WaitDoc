package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import com.team13.WaitDoc.hospital.entity.HospitalMember;
import com.team13.WaitDoc.hospital.repository.HospitalInquiryMemberRepository;
import com.team13.WaitDoc.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.team13.WaitDoc.hospital.entity.HospitalInquiryMemberRole.COUNSELOR;
import static com.team13.WaitDoc.hospital.entity.HospitalMemberRole.DIRECTOR;

@Service
@RequiredArgsConstructor
public class HospitalInquiryMemberService {

    private final HospitalInquiryMemberRepository hospitalInquiryMemberRepository;

    public List<HospitalInquiryMember> findAllByMemberId(Long memberId) {
        return hospitalInquiryMemberRepository.findAllByMemberId(memberId);
    }
}
