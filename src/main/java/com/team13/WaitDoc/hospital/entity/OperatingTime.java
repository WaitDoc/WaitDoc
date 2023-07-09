package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class OperatingTime extends BaseEntity {
    @OneToOne
    private Hospital hospital;

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
}
