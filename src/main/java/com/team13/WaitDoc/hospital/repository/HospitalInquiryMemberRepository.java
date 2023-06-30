package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalInquiryMemberRepository extends JpaRepository<HospitalInquiryMember, Long> {

    List<HospitalInquiryMember> findAllByMemberId(Long memberId);

}
