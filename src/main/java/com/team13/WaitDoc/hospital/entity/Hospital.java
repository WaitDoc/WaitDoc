package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.hospital.dto.HospitalResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class Hospital extends BaseEntity {
    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    private String name;

    @Column(length = 1000)
    private String addr;

    @Column(length = 1000)
    private String introduction;

    @Column(length = 1000)
    private String department;

    private boolean hasER;

    private boolean canAdmit;

    private int bedCount;

    private String classify;

    private String hpid;

    private double latitude;

    private double longitude;

    private int waitingNumber;

    private String tel;

    private LocalTime monStartTime;
    private LocalTime monEndTime;

    private LocalTime tueStartTime;
    private LocalTime tueEndTime;

    private LocalTime wedStartTime;
    private LocalTime wedEndTime;

    private LocalTime thuStartTime;
    private LocalTime thuEndTime;

    private LocalTime friStartTime;
    private LocalTime friEndTime;

    private LocalTime satStartTime;
    private LocalTime satEndTime;

    private LocalTime sunStartTime;
    private LocalTime sunEndTime;

    private LocalTime holidayStartTime;
    private LocalTime holidayEndTime;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<HospitalInquiry> hospitalInquiries = new HashSet<>();

    public List<HospitalInquiry> getInquiries() {
        return new ArrayList<>(hospitalInquiries);
    }

    public HospitalResponseDTO mapToDTO(){
        return HospitalResponseDTO.builder()
                .id(getId())
                .name(name)
                .department(department.split(","))
                .tel(tel)
                .addr(addr)
                .info(introduction)
                .wgs84Lat(latitude)
                .wgs84Lon(longitude)
                .build();
    }


}
