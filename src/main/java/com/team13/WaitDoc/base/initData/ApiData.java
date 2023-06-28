// package com.team13.WaitDoc.base.initData;
//
// import com.team13.WaitDoc.base.util.ApiUt;
// import com.team13.WaitDoc.base.util.HospitalXml;
// import com.team13.WaitDoc.hospital.entity.Hospital;
// import com.team13.WaitDoc.hospital.repository.HospitalRepository;
// import jakarta.annotation.PostConstruct;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import java.io.IOException;
// import java.io.UnsupportedEncodingException;
// import java.util.List;
// import java.util.stream.Collectors;
//
// @Component
// public class ApiData {
//     @Autowired
//     private HospitalRepository hospitalRepository;
//
//     @PostConstruct
//     public void loadData() throws IOException, InterruptedException {
//         int i = 1;
//         while(true){
//             String url = ApiUt.Url.builder()
//                     .page(i++)
//                     .rows(1000)
//                     .build();
//             List<HospitalXml.Item> items = ApiUt.Response.getItems(ApiUt.Response.getBody(url));
//
//             if (items.isEmpty())
//                 break;
//             hospitalRepository.saveAll(items.stream()
//                     .map(HospitalXml.Item::toEntity)
//                     .collect(Collectors.toList()));
//         }
//
//     }
//
// }
