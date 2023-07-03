package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class HospitalDepartment extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Hospital hospital;
    @ManyToOne(fetch = LAZY)
    private Department department;
}
