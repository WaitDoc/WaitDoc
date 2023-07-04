
package com.team13.WaitDoc.base.initData;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.repository.HospitalBulkRepository;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
//@Profile({"prod"})
@Component
public class ApiData {
    @Autowired
    private HospitalBulkRepository hospitalBulkRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    @PostConstruct
    public void loadData() throws IOException, InterruptedException {
        int i = 1;
        while(true){
            List<HospitalXml.Item> items = ApiUt.Response.getItems(CategoryRequestDTO.builder()
                                                                    .page(i++).rows(1000).build());

            if (items.isEmpty())
                break;
//
//            hospitalBulkRepository.bulkSave(items.stream()
//                    .map(HospitalXml.Item::toEntity)
//                    .collect(Collectors.toList()));
            hospitalRepository.saveAll(items.stream()
                    .map(HospitalXml.Item::toEntity)
                    .collect(Collectors.toList()));

        }

    }

}

