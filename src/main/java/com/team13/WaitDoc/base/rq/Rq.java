package com.team13.WaitDoc.base.rq;

import com.team13.WaitDoc.base.rsData.RsData;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Date;
import java.util.Locale;

@Component
@RequestScope
public class Rq {
    /*private final MemberService memberService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private Locale locale;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;

    private Member member = null; // 레이지 로딩, 처음부터 넣지 않고, 요청이 들어올 때 넣는다.

   public Rq(MemberService memberService, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        this.memberService = memberService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.req = req;
        this.resp = resp;
        this.session = session;

        // 현재 로그인한 회원의 인증정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.User = (User) authentication.getPrincipal();
        } else {
            this.User = null;
        }
    }

    // 뒤로가기 + 메세지
    public String historyBack(String msg) {
        String referer = req.getHeader("referer");
        String key = "historyBackErrorMsg___" + referer;
        req.setAttribute("localStorageKeyAboutHistoryBackErrorMsg", key);
        req.setAttribute("historyBackErrorMsg", msg);
        // 200 이 아니라 400 으로 응답코드가 지정되도록
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "usr/common/js";
    }


    // 뒤로가기 + 메세지
    public String historyBack(RsData rsData) {
        return historyBack(rsData.getMsg());
    }

    */

}



