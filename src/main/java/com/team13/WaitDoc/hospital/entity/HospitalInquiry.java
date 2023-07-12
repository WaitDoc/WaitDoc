package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.team13.WaitDoc.hospital.entity.HospitalInquiryMemberRole.COUNSELOR;
import static com.team13.WaitDoc.hospital.entity.HospitalInquiryMemberRole.USER;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class HospitalInquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "hospitalInquiry", cascade = PERSIST)
    @Builder.Default
    private Set<HospitalInquiryMember> hospitalInquiryMembers = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

    public static HospitalInquiry create(String name) {
        return HospitalInquiry.builder()
                .name(name)
                .isActive(true)
                .build();
    }

    public void addUser(Member member) {
        HospitalInquiryMember hospitalInquiryMember = HospitalInquiryMember.builder()
                .member(member)
                .hospitalInquiryMemberRole(USER)
                .hospitalInquiry(this)
                .build();
        hospitalInquiryMembers.add(hospitalInquiryMember);
    }

    public void addCounselor(Member member) {
        HospitalInquiryMember hospitalInquiryMember = HospitalInquiryMember.builder()
                .member(member)
                .hospitalInquiryMemberRole(COUNSELOR)
                .hospitalInquiry(this)
                .build();
        hospitalInquiryMembers.add(hospitalInquiryMember);
    }

    public void unActive() {
        this.isActive = false;
    }
}
