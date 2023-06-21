package com.team13.WaitDoc.Category.controller;

import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    @GetMapping("/all")
    @ResponseBody
    public List showHospital() throws IOException, InterruptedException {
        String url = ApiUt.ApiUrl.builder()
                .page(1)
                .rows(1)
                .region("서울특별시")
                .addr("서대문구")
                .name("삼성")
                .build();

        List<HospitalXml.Item> items = ApiUt.ApiXml.getItems(ApiUt.ApiResponse.getResult(url));

        System.out.println(items.get(0).getDutyName());
        return items;
    }

}

