package com.team13.WaitDoc.category.controller;

import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.dto.HospitalResponseDTO;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    private final HospitalService hospitalService;
    @GetMapping("")
    public String showCategory(){
        return "Category/category";
    }

    @ResponseBody
    @GetMapping("/find")
    public List<HospitalResponseDTO> find(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
        return hospitalService.search(requestDTO);
    }

}

