package com.team13.WaitDoc.hospital.controller;


import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import com.team13.WaitDoc.hospital.service.HospitalMemberService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.waiting.service.WaitingService;

import jakarta.servlet.http.HttpSession;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final WaitingService waitingService;
    private final HospitalInquiryService hospitalInquiryService;
    private final MemberService memberService;
    private final HospitalMemberService hospitalMemberService;

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException {

        model.addAttribute("appKey", AppConfig.getMapKey());
        return "hospital/map";
    }


    @GetMapping("/hospital/{hospitalId}")
    public String hospitalDetail(@PathVariable Long hospitalId, Model model, @AuthenticationPrincipal SecurityUser securityUser){
        Hospital hospital = hospitalService.getHospital(hospitalId);
        String hospitalName = hospitalService.getHospitalName(hospitalId);
        Member member = memberService.findById(securityUser.getMemberId());

        boolean hasAlreadyApplied = hospitalMemberService.hasAlreadyApplied(member, hospital);

        model.addAttribute("hospital", hospital);
        model.addAttribute("hospitalName", hospitalName);
        model.addAttribute("hasAlreadyApplied", hasAlreadyApplied);

        return "hospital/detail";
    }


    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");

        if (sessionMember != null) {
            Long memberId = sessionMember.getMemberId();
            List<Hospital> waitingHospitals = waitingService.getWaitingHospitalsByMemberId(memberId);
            model.addAttribute("waitingHospitals", waitingHospitals);
        }

        return "index";
    }


    @GetMapping("/")
    public String home() {

        return "main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/hospital/{hospitalId}/inquiry")
    public String inquiry(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser) {

        Long hospitalInquiryId = hospitalInquiryService.inquiry(hospitalId, securityUser.getMemberId());

        return "redirect:/hospitalInquiry/" + hospitalInquiryId;
    }

//    @GetMapping("/hospital")
//    public String showMyPage(Model model) {
//        Hospital hospital =hospitalService.findByName();
//        model.addAttribute("hospital", hospital);
//
//        return "hospital/hospitalMain";
//    }
}