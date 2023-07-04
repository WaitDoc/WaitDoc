package com.team13.WaitDoc.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.WaitDoc.base.config.auth.SessionMember;
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

	@PostMapping("/apply/{hospitalId}")
	public String applyForAdmin(@PathVariable Long hospitalId, HttpSession session, Model model){
		SessionMember sessionMember = (SessionMember) session.getAttribute("member");
		Long memberId = sessionMember.getMemberId();
		String memberName = sessionMember.getName();

		Member member = memberService.findById(memberId);
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
}
