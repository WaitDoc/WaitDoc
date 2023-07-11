package com.team13.WaitDoc.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.blacklist.entity.Blacklist;
import com.team13.WaitDoc.hospital.entity.HospitalInquiryMemberRole;
import com.team13.WaitDoc.paper.entity.Paper;
import com.team13.WaitDoc.waiting.entity.Waiting;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.util.Assert;

import lombok.experimental.SuperBuilder;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String email;
    private String gender;
    private String birthday;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Waiting> waitings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Blacklist> blacklists = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paper> papers = new ArrayList<>(); //???

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    @Builder
    public Member(String username, String password) {

        Assert.notNull(username, "username는 널일 수 없습니다.");
        Assert.notNull(password, "password는 널일 수 없습니다.");

        this.name = username;
        //this.password = password;
    }

    public Member(String name, String email, String gender, String birthday, MemberRole memberRole) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.memberRole = memberRole;
    }

    public List<Long> getHospitalIds() {
        return waitings.stream()
            .map(w -> w.getHospital().getId())
            .collect(Collectors.toList());
    }



}
