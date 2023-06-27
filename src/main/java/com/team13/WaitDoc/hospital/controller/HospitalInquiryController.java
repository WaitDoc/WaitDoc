package com.team13.WaitDoc.hospital.controller;

import com.team13.WaitDoc.hospital.entity.HospitalInquiry;
import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HospitalInquiryController {

    private final HospitalInquiryService hospitalInquiryService;

    @GetMapping("/hospital/{hospitalId}/inquiry/{hospitalRoomId}")
    public String enterHospitalChatRoom(
            @PathVariable Long hospitalRoomId,
            @PathVariable String hospitalId,
            @AuthenticationPrincipal SecurityUser securityUser,
            Model model
            ) {

        HospitalInquiry hospitalChatRoom = hospitalInquiryService.enterHospitalChatRoom(hospitalRoomId, securityUser.getId());

        model.addAttribute("hospitalChatRoom", hospitalChatRoom);
        return "hospitalInquiry/detail";
    }

    @GetMapping("/hospital/{hospitalId}/inquiry")
    public ResponseEntity<String> inquiryList(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser) {

        //hospitalInquiryService.findAllByHospitalId(hospitalId, securityUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body("New room added successfully.");
    }
}
