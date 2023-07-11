package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.dto.HospitalResponseDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<HospitalResponseDTO> search(CategoryRequestDTO requestDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name ");
        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getRows(), sort);

        return hospitalRepository.search(requestDTO, pageable)
                .getContent()
                .stream()
                .map(Hospital::mapToDTO)
                .collect(Collectors.toList());
    }

    public void applyForAdmin(Long memberId, Long hospitalId) {
        Member member = memberService.findById(memberId);
        Hospital hospital = findByIdElseThrow(hospitalId);

        hospitalMemberService.applyForAdmin(member, hospital);
    }

    public Hospital toEntity(HospitalXml.Item item){
        if(findByHpid(item.getHpid()).isEmpty())
            return Hospital.builder()
                    .name(item.getDutyName())
                    .addr(item.getDutyAddr())
                    .department(item.getDgidIdName())
                    .latitude(item.getWgs84Lat())
                    .longitude(item.getWgs84Lon())
                    .canAdmit(item.getDutyHayn() == 1)
                    .hpid(item.getHpid())
                    .tel(item.getDutyTel1())
                    .introduction(item.getDutyInf())
                    .build();
        return null;
    }

    public void saveAll(List<Hospital> hospitals) {
        hospitalRepository.saveAll(hospitals);
    }
}
