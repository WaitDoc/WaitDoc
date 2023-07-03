package com.team13.WaitDoc.blacklist.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.team13.WaitDoc.blacklist.entity.Blacklist;
import com.team13.WaitDoc.blacklist.repository.BlacklistRepository;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlacklistService {

	private final BlacklistRepository blacklistRepository;

	public void addAndCount(Hospital hospital, Member member) {
		Blacklist blacklist = blacklistRepository.findByHospitalAndMember(hospital, member)
			.orElse(new Blacklist(member, hospital, 0));

		blacklist.setWarningCount(blacklist.getWarningCount() + 1);

		blacklistRepository.save(blacklist);
	}

	public Optional<Blacklist> getBlacklistByMemberId(Long memberId) {
		return blacklistRepository.findByMemberId(memberId);
	}
}
