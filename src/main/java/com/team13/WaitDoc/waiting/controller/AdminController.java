package com.team13.WaitDoc.waiting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.team13.WaitDoc.blacklist.entity.Blacklist;
import com.team13.WaitDoc.blacklist.service.BlacklistService;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.repository.MemberRepository;
import com.team13.WaitDoc.waiting.dto.WaitingInfo;
import com.team13.WaitDoc.waiting.service.WaitingService;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/waiting")
@RequiredArgsConstructor
public class AdminController {
	private final WaitingService waitingService;

	private final BlacklistService blacklistService;

	private final HospitalRepository hospitalRepository;
	private final MemberRepository memberRepository;

	// 대기자 목록을 보여주는 페이지
	@GetMapping("/{hospitalId}")
	public String showWaitingList(@PathVariable Long hospitalId, Model model) {
		List<WaitingInfo> waitingList = waitingService.getWaitingList(hospitalId);

		for (WaitingInfo waitingInfo : waitingList) {
			Optional<Blacklist> optionalBlacklist = blacklistService.getBlacklistByMemberId(waitingInfo.getMemberId());
			waitingInfo.setWarningCount(optionalBlacklist.isPresent() ? optionalBlacklist.get().getWarningCount() : 0);
		}

		model.addAttribute("waitingList", waitingList);
		return "admin/waitinglist";
	}

	// 대기자를 제거하는 요청을 처리
	@PostMapping("/delete/{hospitalId}/{memberId}")
	public String removeWaiting(@PathVariable Long hospitalId, @PathVariable Long memberId) {
		waitingService.cancelWaiting(hospitalId, memberId);
		return "redirect:/admin/waiting/" + hospitalId;
	}
	@PostMapping("/noShow/{hospitalId}/{memberId}")
	public String noShow(@PathVariable Long hospitalId, @PathVariable Long memberId) {

		Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
		Optional<Member> optionalMember = memberRepository.findById(memberId);

		if (!optionalHospital.isPresent() || !optionalMember.isPresent()) {
			throw new RuntimeException("Hospital or Member not found");
		}

		Hospital hospital = optionalHospital.get();
		Member member = optionalMember.get();

		blacklistService.addAndCount(hospital, member);

		return "redirect:/admin/waiting/" + hospitalId;
	}
}
