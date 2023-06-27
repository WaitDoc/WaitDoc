package com.team13.WaitDoc.member.repository;

import com.team13.WaitDoc.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
