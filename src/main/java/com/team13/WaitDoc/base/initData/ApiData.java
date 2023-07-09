
package com.team13.WaitDoc.base.initData;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.service.DepartmentService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.hospital.service.OperatingTimeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Profile({"prod"})
@Component
@RequiredArgsConstructor
public class ApiData {
    private final HospitalService hospitalService;
    private final OperatingTimeService operatingTimeService;
    private final DepartmentService departmentService;


    @PostConstruct
    public void loadData() throws IOException, InterruptedException {
        int i = 1;
        while(true){
            List<HospitalXml.Item> items = ApiUt.Response.getItems(i++, 1000);

            if (items.isEmpty())
                break;
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

    }

}

