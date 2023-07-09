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
@Table(indexes = @Index(name = "hpid_index", columnList = "hpid"))
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

    @Column(name = "hpid", unique = true)
    private String hpid;

    private double latitude;

    private double longitude;

    private int waitingNumber;

    private String tel;

    @OneToOne(mappedBy = "hospital")
    private OperatingTime operatingTime;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<HospitalInquiry> hospitalInquiries = new HashSet<>();

    public List<HospitalInquiry> getInquiries() {
        return new ArrayList<>(hospitalInquiries);
    }

    public HospitalResponseDTO mapToDTO(){
        String[] departmentsArray;
        if (department != null) {
            departmentsArray = department.split(",");
        } else {
            departmentsArray = new String[0];
        }
        return HospitalResponseDTO.builder()
                .id(getId())
                .name(name)
                .department(departmentsArray)
                .tel(tel)
                .addr(addr)
                .info(introduction)
                .wgs84Lat(latitude)
                .wgs84Lon(longitude)
                .nightDays(operatingTime.getNightDays().split(" "))
                .holiday(operatingTime.getHolidayStartTime() != null
                        && operatingTime.getHolidayEndTime() != null)
                .saturday(operatingTime.getSatStartTime() != null
                        && operatingTime.getSatEndTime() != null)
                .sunday(operatingTime.getSunStartTime() != null
                        && operatingTime.getSunEndTime() != null)
                .canAdmit(canAdmit)
                .build();
    }


}
