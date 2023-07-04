package com.team13.WaitDoc.hospital.service;

import java.util.List;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.HospitalMember;
import com.team13.WaitDoc.hospital.entity.HospitalMemberRole;
import com.team13.WaitDoc.hospital.repository.HospitalMemberRepository;
import com.team13.WaitDoc.member.entity.Member;

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
}
