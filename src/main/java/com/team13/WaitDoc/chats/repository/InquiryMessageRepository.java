package com.team13.WaitDoc.chats.repository;


import com.team13.WaitDoc.chats.entity.InquiryMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryMessageRepository extends JpaRepository<InquiryMessage, Long> {
    List<InquiryMessage> findByHospitalInquiryId(Long roomId);
}

