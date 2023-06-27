package com.team13.WaitDoc.hospital.controller;

import com.team13.WaitDoc.hospital.service.HospitalInquiryService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AjaxHospitalInquiryController {

    private final HospitalInquiryService hospitalInquiryService;

    @PostMapping("/hospital/{hospitalId}/inquiry")
    public ResponseEntity<String> inquiry(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser) {

        hospitalInquiryService.inquiry(hospitalId, securityUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body("New room added successfully.");
    }
}
