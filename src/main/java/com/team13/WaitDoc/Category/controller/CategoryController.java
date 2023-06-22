package com.team13.WaitDoc.Category.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team13.WaitDoc.Category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.Category.DTO.HospitalResponseDTO;
import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    @GetMapping("")
    public String showCategory(){
        return "Category/category";
    }
    @GetMapping("/all")
    @ResponseBody
    public List<HospitalResponseDTO> showHospital() throws IOException, InterruptedException {
        String url = ApiUt.ApiUrl.builder()
                .page(1)
                .rows(40)
                .region("서울특별시")
                .addr("강남구")
                .classify("A")
                .department("D024")
                .build();
        System.out.println(ApiUt.ApiResponse.getResult(url));

        List<HospitalXml.Item> items = ApiUt.ApiXml.getItems(ApiUt.ApiResponse.getResult(url));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(items, new TypeReference<List<HospitalResponseDTO>>() {});
    }
    @ResponseBody
    @GetMapping("/find")
    public CategoryRequestDTO find(CategoryRequestDTO requestDTO) throws UnsupportedEncodingException {
        System.out.println(">>>>>>>>>>>>>>>>>>>"+ApiUt.ApiUrl.DtoToUrl(requestDTO));
        return requestDTO;
    }

}

