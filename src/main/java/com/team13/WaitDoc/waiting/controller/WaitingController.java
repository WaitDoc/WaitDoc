package com.team13.WaitDoc.waiting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.service.HospitalService;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.waiting.dto.WaitingRequest;
import com.team13.WaitDoc.waiting.service.WaitingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/waiting")
@RequiredArgsConstructor
public class WaitingController {
	private final WaitingService waitingService;
	private final HospitalService hospitalService;
	private final MemberService memberService;

	@GetMapping("/{hospitalId}")
	public String showWaitingPage(@PathVariable Long hospitalId, Model model) {
		int waitingCount = waitingService.getWaitingCount(hospitalId);
		Hospital hospital = hospitalService.getHospital(hospitalId);
		String hospitalName = hospitalService.getHospitalName(hospitalId);
		Member member = memberService.getMember(hospitalId);

		model.addAttribute("waitingCount", waitingCount);
		model.addAttribute("hospital", hospital);
		model.addAttribute("hospitalName", hospitalName);
		model.addAttribute("member", member);
		return "waiting/waitingpage";
	}

	@PostMapping("/{hospitalId}")
	public String addWaitingPatient(@PathVariable Long hospitalId, HttpSession session) {
		SessionMember sessionMember = (SessionMember)session.getAttribute("member");

		Long memberId = sessionMember.getMemberId();
		String name = sessionMember.getName();

		WaitingRequest request = new WaitingRequest();
		request.setHospitalId(hospitalId);
		request.setMemberId(memberId);
		request.setName(name);
		request.setType("register");

		ObjectMapper mapper = new ObjectMapper();
		String waitingJson = "";
		try {
			waitingJson = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		waitingService.send("hospital_waiting", String.valueOf(hospitalId), waitingJson);
		//대기자 수 증가
		waitingService.registerWaiting(hospitalId, memberId);
		return "redirect:/waiting/" + hospitalId;
	}

	@PostMapping("/delete/{hospitalId}")
	public String deleteWaitingPatient(@PathVariable Long hospitalId, HttpSession session) {
		SessionMember sessionMember = (SessionMember)session.getAttribute("member");

		Long memberId = sessionMember.getMemberId();
		String name = sessionMember.getName();

		WaitingRequest request = new WaitingRequest();
		request.setHospitalId(hospitalId);
		request.setMemberId(memberId);
		request.setName(name);
		request.setType("cancel"); // 예약 취소를 위해 setType을 cancel로 설정

		ObjectMapper mapper = new ObjectMapper();
		String waitingJson = "";
		try {
			waitingJson = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		waitingService.send("hospital_waiting", String.valueOf(hospitalId), waitingJson);

		// 대기자 수 감소
		waitingService.cancelWaiting(hospitalId, memberId);

		return "redirect:/waiting/" + hospitalId;
	}

}


