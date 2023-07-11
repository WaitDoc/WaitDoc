package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.*;
import com.team13.WaitDoc.hospital.repository.HospitalInquiryRepository;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.team13.WaitDoc.hospital.entity.HospitalInquiryMemberRole.COUNSELOR;
import static com.team13.WaitDoc.hospital.entity.HospitalMemberRole.DIRECTOR;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalInquiryService {

    private final HospitalService hospitalService;
    private final MemberService memberService;
    private final HospitalInquiryRepository hospitalInquiryRepository;
    private final HospitalInquiryMemberService hospitalInquiryMemberService;
    private final HospitalMemberService hospitalMemberService;

    public Long inquiry(Long hospitalId, Long memberId) {
        HospitalInquiry hospitalInquiry = createAndSave(hospitalId);

        Member member = memberService.findByIdElseThrow(memberId);
        hospitalInquiry.addUser(member);

        return hospitalInquiry.getId();
    }


    public HospitalInquiry createAndSave(Long hospitalId) {
        Hospital hospital = hospitalService.findByIdElseThrow(hospitalId);

        HospitalInquiry hospitalInquiry = HospitalInquiry.builder()
                .hospital(hospital)
                .build();

        return hospitalInquiryRepository.save(hospitalInquiry);
    }

    public HospitalInquiry findByIdElseThrow(Long hospitalInquiryId) {
        return hospitalInquiryRepository.findById(hospitalInquiryId)
                .orElseThrow();
    }

    public HospitalInquiry enterHospitalInquiry(Long hospitalInquiryId, Long memberId) {
        HospitalInquiry hospitalInquiry = findByIdElseThrow(hospitalInquiryId);

        Optional<HospitalInquiryMember> inquiryMemberOptional = hospitalInquiry.getHospitalInquiryMembers().stream()
                .filter(hospitalInquiryMember -> hospitalInquiryMember.getMember().getId().equals(memberId))
                .findFirst();

        if(inquiryMemberOptional.isPresent()){
            return hospitalInquiry;
        }

        HospitalMember hospitalMember = hospitalMemberService.findByMemberIdElseThrow(memberId);
        if(hospitalMember.getRole()!=DIRECTOR){
            throw new IllegalArgumentException("병원장이 아닙니다.");
        }

        hospitalInquiry.addCounselor(hospitalMember.getMember());

        return hospitalInquiry;
    }




    // 병원장이 모든 내역을 보고싶다
    public List<HospitalInquiry> findAllByHospitalIdAndMemberId(Long hospitalId, Long memberId) {
        HospitalMember hospitalMember = hospitalMemberService.findByMemberIdElseThrow(memberId);

        if (!hospitalMember.getHospital().getId().equals(hospitalId)) {
            throw new IllegalArgumentException("병원에 권한이 없습니다.");
        }

        if (!hospitalMember.getRole().equals(DIRECTOR)) {
            throw new IllegalArgumentException("DIRECTOR에게 권한을 요청하세요.");
        }

        return hospitalInquiryRepository.findAllByHospitalId(hospitalId);
    }

    public void endInquiry(Long hospitalInquiryId) {
        HospitalInquiry hospitalInquiry = findByIdElseThrow(hospitalInquiryId);
        hospitalInquiry.unActive();
    }

    public void deleteInquiry(Long hospitalInquiryId) {
        HospitalInquiry hospitalInquiry = findByIdElseThrow(hospitalInquiryId);
        hospitalInquiryRepository.deleteById(hospitalInquiry.getId());
    }

    public List<HospitalInquiry> findAllByMemberId(Long memberId) {
        return hospitalInquiryMemberService.findAllByMemberId(memberId).stream()
                .map(HospitalInquiryMember::getHospitalInquiry)
                .toList();
    }
}
