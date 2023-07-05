package com.team13.WaitDoc.base.initData;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Department;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.DepartmentRepository;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.service.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData (HospitalRepository hospitalRepository, DepartmentService departmentService) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
                List<HospitalXml.Item> items = ApiUt.Response.getItems(
                        CategoryRequestDTO.builder().page(1).rows(1000).build());
                List<Hospital> hospitals = items.stream()
                        .map(HospitalXml.Item::toHospitalEntity)
                        .toList();
                hospitalRepository.saveAll(hospitals);
                departmentService.saveAll(hospitals);
            }
        };
    }
}