package com.team13.WaitDoc.waiting.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Builder
@Entity
public class Waiting extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

}

