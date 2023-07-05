package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class HospitalInquiryMember { //hospitalInquiryMember

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private HospitalInquiry hospitalInquiry;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public HospitalInquiryMember(Member member, HospitalInquiry hospitalInquiry) {
        this.member = member;
        this.hospitalInquiry = hospitalInquiry;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setName() {
        this.member = member;
    }

}