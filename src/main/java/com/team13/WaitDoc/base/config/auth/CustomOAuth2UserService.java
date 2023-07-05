package com.team13.WaitDoc.base.config.auth;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.security.SecurityUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest); // OAuth2 정보 가져오기
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져오기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = save(attributes);

        //세션 멤버 객체 추가
        SessionMember sessionMember = new SessionMember(member);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("member", sessionMember);

        SecurityUser securityUser = SecurityUser.builder()
            .name(member.getName())
            .email(member.getEmail())
            .memberId(member.getId())
            .authorities(Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getKey())))
            .build();

        return securityUser;
    }


    private Member save(OAuthAttributes attributes) {
        return memberService.save(attributes);
    }
}
