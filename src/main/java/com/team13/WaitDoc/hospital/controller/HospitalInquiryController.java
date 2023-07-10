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
        HospitalInquiry hospitalInquiry = hospitalInquiryService.enterHospitalInquiry(hospitalInquiryId, securityUser.getMemberId());

        model.addAttribute("hospitalInquiry", hospitalInquiry);
        return "chatting/hospitalInquiry/detail";
    }

    @GetMapping("/hospital/{hospitalId}/chatList")
    public String hospitalMemberInquiryList(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser, Model model) {
        List<HospitalInquiry> hospitalInquiries = hospitalInquiryService.findAllByHospitalIdAndMemberId(hospitalId, securityUser.getMemberId());
        model.addAttribute("hospitalInquires", hospitalInquiries);
        return "chatting/chatlist/chatlist";
    }

    @GetMapping("/member/chatList")
    public String memberInquiryList(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
        List<HospitalInquiry> hospitalInquiries = hospitalInquiryService.findAllByMemberId(securityUser.getMemberId());
        model.addAttribute("hospitalInquiries", hospitalInquiries);
        return "chatting/chatlist/chatlist";
    }

//    @PostMapping("/hospitalInquiry/{hospitalInquiryId}/endChat")
//    public String endChat(@PathVariable Long hospitalInquiryId) {
//        return endInquiry(hospitalInquiryId);
//    }

    @PostMapping("/{hospitalInquiryId}/endChat")
    public String endInquiry(@PathVariable Long hospitalInquiryId) {
        hospitalInquiryService.endInquiry(hospitalInquiryId);
        return "redirect:/member/chatList";
    }

    @PostMapping("/{hospitalInquiryId}/delete")
    public String deleteInquiry(@PathVariable Long hospitalInquiryId) {
        hospitalInquiryService.deleteInquiry(hospitalInquiryId);
        return "redirect:/member/chatList";
    }

}
