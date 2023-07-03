package com.team13.WaitDoc.hospital.controller;


import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException, InterruptedException {

        model.addAttribute("appKey", AppConfig.getMapKey());
        return "hospital/map";
    }

    @GetMapping("/hospital/{hospitalId}")
    public String hospitalDetail(@PathVariable Long hospitalId, Model model){
        Hospital hospital = hospitalService.getHospital(hospitalId);
        String hospitalName = hospitalService.getHospitalName(hospitalId);

        model.addAttribute("hospital", hospital);
        model.addAttribute("hospitalName", hospitalName);

        return "hospital/detail";
    }
}
