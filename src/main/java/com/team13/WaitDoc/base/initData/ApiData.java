
package com.team13.WaitDoc.base.initData;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
//@Profile({"prod"})
@Component
@RequiredArgsConstructor
public class ApiData {
    private final HospitalRepository hospitalRepository;
    private final DepartmentService departmentService;


    @PostConstruct
    public void loadData() throws IOException, InterruptedException {
        int i = 1;
        while(true){
            List<HospitalXml.Item> items = ApiUt.Response.getItems(CategoryRequestDTO.builder()
                                                                    .page(i++).rows(1000).build());

            if (items.isEmpty())
                break;
//            hospitalBulkRepository.bulkSave(items.stream()
//                    .map(HospitalXml.Item::toEntity)
//                    .collect(Collectors.toList()));
            List<Hospital> hospitals = items.stream()
                    .map(HospitalXml.Item::toHospitalEntity)
                    .toList();
            hospitalRepository.saveAll(hospitals);
            departmentService.saveAll(hospitals);

        }

    }

}

