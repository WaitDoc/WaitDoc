package com.team13.WaitDoc.hospital.controller;


import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import com.team13.WaitDoc.hospital.service.HospitalService;

import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final HospitalInquiryService hospitalInquiryService;

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException {

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

    @PostMapping("/hospital/{hospitalId}/inquiry")
    public String inquiry(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser) {

        Long hospitalInquiryId = hospitalInquiryService.inquiry(hospitalId, securityUser.getMemberId());

        return "redirect:/hospitalInquiry/" + hospitalInquiryId;
    }
}
