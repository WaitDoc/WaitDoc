package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.*;
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
    private String address;

    @Column(length = 1000)
    private String introduction;

    private String department;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int waitingNumber;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalInquiry> hospitalChatRooms = new HashSet<>();

    public Set<HospitalInquiry> getHospitalChatRooms() {
        return hospitalChatRooms;
    }

}
