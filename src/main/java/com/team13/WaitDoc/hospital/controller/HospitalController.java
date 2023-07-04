package com.team13.WaitDoc.hospital.controller;


import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.waiting.service.WaitingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final WaitingService waitingService;

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

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        SessionMember sessionMember = (SessionMember)session.getAttribute("member");

        if (sessionMember != null) {
            Long memberId = sessionMember.getMemberId();
            List<Hospital> waitingHospitals = waitingService.getWaitingHospitalsByMemberId(memberId);
            model.addAttribute("waitingHospitals", waitingHospitals);
        }

        return "index";
    }
}
