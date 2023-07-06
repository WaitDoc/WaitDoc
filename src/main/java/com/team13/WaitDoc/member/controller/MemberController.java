package com.team13.WaitDoc.member.controller;


import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



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
    public String showMyPage(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Member member = memberService.findByName(securityUser.getName());
        model.addAttribute("member", member);
        return "member/mypage";
    }



}
