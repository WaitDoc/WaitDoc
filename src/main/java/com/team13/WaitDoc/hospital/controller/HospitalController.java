package com.team13.WaitDoc.hospital.controller;

import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException, InterruptedException {
        String url = ApiUt.ApiUrl.builder()
                .page(1)
                .rows(2)
                .region("서울특별시")
                .addr("서대문구")
                .name("삼성")
                .build();

        model.addAttribute("appKey", AppConfig.getMapKey());

        List<HospitalXml.Item> items = ApiUt.ApiXml.getItems(ApiUt.ApiResponse.getResult(url));

        System.out.println("Generated URL: " + ApiUt.ApiResponse.getResult(url));


        model.addAttribute("items", items);

        return "hospital/map";
    }
}