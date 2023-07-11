package com.team13.WaitDoc.member.controller;


import com.team13.WaitDoc.base.config.auth.SessionMember;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.paper.dto.PaperDto;
import com.team13.WaitDoc.paper.entity.Paper;
import com.team13.WaitDoc.paper.service.PaperService;
import com.team13.WaitDoc.security.SecurityUser;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;



    @GetMapping("/login")
    public String join() {
        return "member/login";
    }

    @GetMapping("/mypage")
    public String showMyPage(Model model, HttpSession session) {
        Object memberObj = session.getAttribute("member");
        if (memberObj instanceof SessionMember) {
            SessionMember sessionMember = (SessionMember) memberObj;
            String memberName = sessionMember.getName();
            model.addAttribute("member", memberName);

        } else {

        }
        return "member/mypage";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Member member = memberService.findByName(securityUser.getName());
        model.addAttribute("member", member);
        return "member/profile";
    }

    @GetMapping("/modify")
    public String modify(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Member member = memberService.findByName(securityUser.getName());
        model.addAttribute("member", member);
        return "member/modify";
    }







}
