package com.team13.WaitDoc.member.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberUpdateDto {
	private String name;
	private String email;


}
