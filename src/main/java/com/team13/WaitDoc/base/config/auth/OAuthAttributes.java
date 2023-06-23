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

        return null;
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

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .gender(gender)
                .birthday(birthday)
                .memberRole(MemberRole.ROLE_USER)
                .build();
    }
}
