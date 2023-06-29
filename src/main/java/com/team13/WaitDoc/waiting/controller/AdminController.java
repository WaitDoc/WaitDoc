package com.team13.WaitDoc.waiting.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.team13.WaitDoc.waiting.dto.WaitingInfo;
import com.team13.WaitDoc.waiting.service.WaitingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/waiting")
@RequiredArgsConstructor
public class AdminController {
	private final WaitingService waitingService;

	// 대기자 목록을 보여주는 페이지
	@GetMapping("/{hospitalId}")
	public String showWaitingList(@PathVariable Long hospitalId, Model model) {
		List<WaitingInfo> waitingList = waitingService.getWaitingList(hospitalId);
		model.addAttribute("waitingList", waitingList);
		return "admin/waitinglist";
	}

	// 대기자를 제거하는 요청을 처리
	@PostMapping("/delete/{hospitalId}/{memberId}")
	public String removeWaiting(@PathVariable Long hospitalId, @PathVariable Long memberId) {
		waitingService.cancelWaiting(hospitalId, memberId);
		return "redirect:/admin/waiting/" + hospitalId;
	}
}
