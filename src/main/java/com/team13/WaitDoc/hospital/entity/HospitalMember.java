package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalMember {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    private HospitalMemberRole role;

    public HospitalMember(Member member, Hospital hospital) {
        this.member = member;
        this.hospital = hospital;
        this.role = HospitalMemberRole.APPLIED;  // 신청 상태로 초기화
    }


}

