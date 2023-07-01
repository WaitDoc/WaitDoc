package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import com.team13.WaitDoc.hospital.repository.HospitalInquiryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalInquiryMemberService {

    private final HospitalInquiryMemberRepository hospitalInquiryMemberRepository;

    public List<HospitalInquiryMember> findAllByMemberId(Long memberId) {
        return hospitalInquiryMemberRepository.findAllByMemberId(memberId);
    }
}
