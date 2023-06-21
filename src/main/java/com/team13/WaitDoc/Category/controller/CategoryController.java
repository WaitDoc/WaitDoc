package com.team13.WaitDoc.Category.controller;

import com.team13.WaitDoc.base.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    @GetMapping("/all")
    @ResponseBody
    public String showHospital() throws IOException, InterruptedException {
        String u = Ut.ApiUrl.builder()
                .page(1)
                .rows(10)
                .region("서울특별시")
                .addr("서대문구")
                .name("삼성")
                .build();
        return Ut.ApiResponse.getBody(u);
    }

}
