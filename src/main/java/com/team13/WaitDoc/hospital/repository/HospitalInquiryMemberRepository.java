package com.team13.WaitDoc.chats.repository;

import com.team13.WaitDoc.hospital.entity.HospitalInquiryMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<HospitalInquiryMember, Long> {
}
