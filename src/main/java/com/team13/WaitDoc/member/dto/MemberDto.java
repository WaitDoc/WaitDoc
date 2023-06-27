package com.team13.WaitDoc.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team13.WaitDoc.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("created_at")
    private LocalDateTime createDate;

    @JsonProperty("updated_at")
    private LocalDateTime modifyDate;

    /*public static MemberDto fromUser(Member member) {
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .username(member.getName())
                .createDate(Member.getCreateDate())
                .modifyDate(Member.getModifyDate())
                .build();

        return memberDto;
    }

     */

}
