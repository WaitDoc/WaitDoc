package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.category.DTO.HospitalResponseDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final HospitalMemberService hospitalMemberService;
    private final MemberService memberService;
    public Optional<Hospital> findByHpid (String hpid) {
        return hospitalRepository.findByHpid(hpid);
    }

    public Hospital findByIdElseThrow(Long hospitalId) {
        return hospitalRepository.findById(hospitalId).orElseThrow();
    }

    public String getHospitalName(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid hospital Id:" + hospitalId));
        return hospital.getName();
    }

    public Hospital getHospital(Long hospitalId) {
        return hospitalRepository.findById(hospitalId)
            .orElseThrow(() -> new NoSuchElementException("No hospital found with ID: " + hospitalId));
    }

    // public List<Hospital> search(CategoryRequestDTO requestDTO) {
    //     return hospitalRepository.search(requestDTO);
    // }

    public void applyForAdmin(Long memberId, Long hospitalId) {
        Member member = memberService.findById(memberId);
        Hospital hospital = findByIdElseThrow(hospitalId);

        hospitalMemberService.applyForAdmin(member, hospital);
    }



}
