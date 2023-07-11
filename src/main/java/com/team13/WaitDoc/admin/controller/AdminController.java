package com.team13.WaitDoc.admin.controller;

import java.util.List;


import com.team13.WaitDoc.admin.dto.AdminAuthRequest;
import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.security.SecurityUser;
import com.team13.WaitDoc.admin.service.AdminService;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.HospitalMember;
import com.team13.WaitDoc.hospital.service.HospitalMemberService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	private final HospitalMemberService hospitalMemberService;
	private final HospitalService hospitalService;
	private final Environment environment;
	private final AdminService adminService;
	private final String adminKey = "1122334455";

	@PostMapping("/apply/{hospitalId}")
	public String applyForAdmin(@PathVariable Long hospitalId, @AuthenticationPrincipal SecurityUser securityUser){

		Member member = memberService.findById(securityUser.getMemberId());
		Hospital hospital = hospitalService.getHospital(hospitalId);

		hospitalMemberService.applyForAdmin(member, hospital);

		return "redirect:/hospital/" + hospitalId;
	}

	@GetMapping("/apply")
	public String getAdminApplications(Model model) {
		List<HospitalMember> applications = hospitalMemberService.getAllApplications();
		model.addAttribute("hospitalApplications", applications);
		return "admin/apply";
	}

	@PostMapping("/approve/{applicationId}")
	public String approveApplication(@PathVariable Long applicationId) {
		hospitalMemberService.approveApplication(applicationId);
		return "redirect:/admin/apply";
	}

	@GetMapping("/check-key")
	public String checkAdminkeyapply(){
		return "admin/adminkey";
	}

	@GetMapping("/adminhome")
	public String adminHome(){
		return "admin/adminhome";
	}

	@PostMapping("/check-key")
	public String checkAdminKey(@RequestParam("adminKey") String inputKey, HttpSession session) {
		SessionMember sessionMember = (SessionMember)session.getAttribute("member");
		Long memberId = sessionMember.getMemberId();

		if (inputKey.equals(adminKey)) {
			memberService.setRoleAsAdmin(memberId, inputKey);
			return "redirect:/admin/adminhome";
		} else
			return "redirect:/admin/key-error"; // 관리자 키 오류 페이지로 이동합니다.
		}


	@GetMapping("/admin/key-error")
	public String showAdminKeyErrorPage() {
		return "admin/key-error";
	}


}
