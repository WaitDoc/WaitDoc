package com.team13.WaitDoc.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HospitalInquiryMemberRole {
    USER("사용자"),
    COUNSELOR("상담사"); //상담사 권한

    private final String nickname;

}
