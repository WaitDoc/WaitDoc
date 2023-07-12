package com.team13.WaitDoc.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.member.entity.MemberRole;
import com.team13.WaitDoc.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final MemberRepository memberRepository;

	@Transactional
	public void upgradeToAdmin(Long userId) {
		Member member = memberRepository.findById(userId).orElseThrow(
			() -> new IllegalArgumentException("No user found with id: " + userId)
		);

		member.setMemberRole(MemberRole.ROLE_ADMIN);
		memberRepository.save(member);
	}
}
