package com.team13.WaitDoc.member.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NoArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String join() {
        return "member/login";
    }
}
