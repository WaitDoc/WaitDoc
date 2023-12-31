package com.team13.WaitDoc.base.config.auth;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String nickname;
    private final String email;
    private final String gender;
    private final String birthday;


    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String nickname, String email, String gender, String birthday) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes) {
        // 카카오
        if ("kakao".equals(socialName)) {
            return ofKakao(userNameAttributeName, attributes);
        }
        //네이버
        if("naver".equals(socialName)){
            return ofNaver(userNameAttributeName,attributes);
        }

        if("google".equals(socialName)){
            System.out.println(attributes);
            return ofGoogle(userNameAttributeName,attributes);
        }
        return null;
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nickname((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .gender((String)attributes.get("gender"))
                .birthday((String)attributes.get("birthday"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .nickname((String) response.get("name"))
                .email((String) response.get("email"))
                .gender((String)response.get("gender"))
                .birthday((String)response.get("birthday"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .nickname((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .gender((String) kakaoAccount.get("gender"))
                .birthday((String) kakaoAccount.get("birthday"))
                .attributes(attributes)
                .build();
    }


}
