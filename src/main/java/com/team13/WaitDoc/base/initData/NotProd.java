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
import java.util.stream.Collectors;
@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData (HospitalRepository hospitalRepository, DepartmentService departmentService) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
//                List<Department> departments = List.of(
//                        Department.builder().name("내과").code("D001").build(),
//                        Department.builder().name("소아청소년과").code("D002").build(),
//                        Department.builder().name("신경과").code("D003").build(),
//                        Department.builder().name("정신건강의학과").code("D004").build(),
//                        Department.builder().name("피부과").code("D005").build(),
//                        Department.builder().name("외과").code("D006").build(),
//                        Department.builder().name("흉부외과").code("D007").build(),
//                        Department.builder().name("정형외과").code("D008").build(),
//                        Department.builder().name("신경외과").code("D009").build(),
//                        Department.builder().name("성형외과").code("D010").build(),
//                        Department.builder().name("산부인과").code("D011").build(),
//                        Department.builder().name("안과").code("D012").build(),
//                        Department.builder().name("이비인후과").code("D013").build(),
//                        Department.builder().name("비뇨기과").code("D014").build(),
//                        Department.builder().name("재활의학과").code("D016").build(),
//                        Department.builder().name("마취통증의학과").code("D017").build(),
//                        Department.builder().name("영상의학과").code("D018").build(),
//                        Department.builder().name("치료방사선과").code("D019").build(),
//                        Department.builder().name("임상병리과").code("D020").build(),
//                        Department.builder().name("해부병리과").code("D021").build(),
//                        Department.builder().name("가정의학과").code("D022").build(),
//                        Department.builder().name("핵의학과").code("D023").build(),
//                        Department.builder().name("응급의학과").code("D024").build(),
//                        Department.builder().name("치과").code("D026").build(),
//                        Department.builder().name("구강악안면외과").code("D034").build()
//                );
                //departmentRepository.saveAll(departments);
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