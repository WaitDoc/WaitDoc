package com.team13.WaitDoc.category.controller;

import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.category.DTO.HospitalResponseDTO;
import com.team13.WaitDoc.base.util.ApiUt;
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
    @GetMapping("/all")
    @ResponseBody
    public List<HospitalResponseDTO> showHospital() throws IOException, InterruptedException {

        //return ApiUt.Response.getResponseDTOs(CategoryRequestDTO.builder().build());
        return null;
    }

    @GetMapping("/db")
    @ResponseBody
    public List<HospitalResponseDTO> findFromDB(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
        hospitalService.search(requestDTO);
        //return ApiUt.Response.getResponseDTOs(CategoryRequestDTO.builder().build());
        return null;
    }
    @ResponseBody
    @GetMapping("/find")
    public List<Hospital> find(@ModelAttribute CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
//        return ApiUt.Response.getResponseDTOs(requestDTO);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+requestDTO);

        return hospitalService.search(requestDTO);
    }

}

