package com.team13.WaitDoc.hospital.controller;

import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalInquiryController {

    private final HospitalInquiryService hospitalInquiryService;

    @GetMapping("/hospitalInquiry/{hospitalInquiryId}")
    public String enterHospitalChatRoom(
            @PathVariable Long hospitalInquiryId,
            @AuthenticationPrincipal SecurityUser securityUser,
            Model model
            ) {
        HospitalInquiry hospitalChatRoom = hospitalInquiryService.enterHospitalInquiry(hospitalInquiryId, securityUser.getId());

        model.addAttribute("hospitalChatRoom", hospitalChatRoom);
        return "hospitalInquiry/detail";
    }

    @GetMapping("/hospital/{hospitalId}/chatList")
    public String hospitalMemberInquiryList(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser, Model model) {
        List<HospitalInquiry> hospitalInquiries = hospitalInquiryService.findAllByHospitalIdAndMemberId(hospitalId, securityUser.getId());
        model.addAttribute("hospitalInquires", hospitalInquiries);
        return "chatlist/chatlist";
    }

    @GetMapping("/chatList")
    public String memberInquiryList(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
        List<HospitalInquiry> hospitalInquiries = hospitalInquiryService.findAllByMemberId(securityUser.getId());
        model.addAttribute("hospitalInquiry", hospitalInquiries);
        return "chatlist/chatlist";
    }

    @PostMapping("/{hospitalInquiryId}/endChat")
    public String endInquiry(@PathVariable Long hospitalInquiryId) {
        hospitalInquiryService.endInquiry(hospitalInquiryId);
        return "redirect:/chatlist/chatlist";
    }

    @PostMapping("/{hospitalInquiryId}/delete")
    public String deleteInquiry(@PathVariable Long hospitalInquiryId) {
        hospitalInquiryService.deleteInquiry(hospitalInquiryId);
        return "redirect:/chatlist/chatlist";
    }




}
