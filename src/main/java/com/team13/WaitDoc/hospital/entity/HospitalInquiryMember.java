package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class HospitalInquiryMember extends BaseEntity { //hospitalInquiryMember

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private HospitalInquiry hospitalInquiry;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private HospitalInquiryMemberRole hospitalInquiryMemberRole;


    @Builder
    public HospitalInquiryMember(Member member, HospitalInquiry hospitalInquiry, HospitalInquiryMemberRole hospitalInquiryMemberRole) {
        this.member = member;
        this.hospitalInquiry = hospitalInquiry;
        this.hospitalInquiryMemberRole = hospitalInquiryMemberRole;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setName() {
        this.member = member;
    }
    public Member getMember() {
        return member;
    }

    public void setRole(HospitalInquiryMemberRole role) {
        this.hospitalInquiryMemberRole = role;
    }


}
