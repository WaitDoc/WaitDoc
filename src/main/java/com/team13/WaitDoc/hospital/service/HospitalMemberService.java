package com.team13.WaitDoc.hospital.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.team13.WaitDoc.hospital.entity.*;
import com.team13.WaitDoc.hospital.repository.HospitalInquiryMemberRepository;
import com.team13.WaitDoc.hospital.repository.HospitalMemberRepository;
import com.team13.WaitDoc.member.entity.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalMemberService {

    private final HospitalMemberRepository hospitalMemberRepository;
    private final HospitalInquiryMemberRepository hospitalInquiryMemberRepository;

    public HospitalMember findByMemberIdElseThrow(Long memberId) {
        return hospitalMemberRepository.findByMemberId(memberId)
                .orElseThrow();
    }


    public void applyForAdmin(Member member, Hospital hospital) {
        HospitalMember hospitalMember = new HospitalMember();
        hospitalMember.setMember(member);
        hospitalMember.setHospital(hospital);
        hospitalMember.setRole(HospitalMemberRole.APPLIED);

        hospitalMemberRepository.save(hospitalMember);
    }
    public List<HospitalMember> getAllApplications() {
        return hospitalMemberRepository.findAllByRole(HospitalMemberRole.APPLIED);
    }

    public void approveApplication(Long applicationId) {
        HospitalMember application = hospitalMemberRepository.findById(applicationId)
            .orElseThrow(() -> new NoSuchElementException("No application found with ID: " + applicationId));
        application.setRole(HospitalMemberRole.DIRECTOR);

        hospitalMemberRepository.save(application);
    }

    public boolean hasAlreadyApplied(Member member, Hospital hospital) {
        return hospitalMemberRepository.existsByMemberAndHospital(member, hospital);
    }
}
