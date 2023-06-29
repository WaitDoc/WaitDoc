package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class Hospital extends BaseEntity {
    private String name;

    @Column(length = 1000)
    private String addr;

    @Column(length = 1000)
    private String introduction;

    private String department;

    private String hpid;

    private double latitude;

    private double longitude;

    private int waitingNumber;

    private int monStartTime;
    private int monEndTime;

    private int tueStartTime;
    private int tueEndTime;

    private int wedStartTime;
    private int wedEndTime;

    private int thuStartTime;
    private int thuEndTime;

    private int friStartTime;
    private int friEndTime;

    private int satStartTime;
    private int satEndTime;

    private int sunStartTime;
    private int sunEndTime;

    private int holidayStartTime;
    private int holidayEndTime;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalInquiry> hospitalChatRooms = new HashSet<>();

    public Set<HospitalInquiry> getHospitalChatRooms() {
        return hospitalChatRooms;
    }

}
