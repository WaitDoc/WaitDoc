package com.team13.WaitDoc.base.initData;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Department;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import com.team13.WaitDoc.hospital.repository.DepartmentRepository;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.service.DepartmentService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.hospital.service.OperatingTimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData (HospitalService hospitalService,
                                OperatingTimeService operatingTimeService,
                                DepartmentService departmentService) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
                List<HospitalXml.Item> items = ApiUt.Response.getItems(1, 1000);
                List<Hospital> hospitals = items.stream()
                        .map(hospitalService::toEntity)
                        .filter(Objects::nonNull)
                        .toList();
                hospitalService.saveAll(hospitals);

                List<OperatingTime> operatingTimes = items.stream()
                        .map(operatingTimeService::toEntity)
                        .filter(Objects::nonNull)
                        .toList();
                operatingTimeService.savaAll(operatingTimes);
                departmentService.saveAll(hospitals);
            }
        };
    }
}