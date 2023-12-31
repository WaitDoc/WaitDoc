package com.team13.WaitDoc.member.repository;

import com.team13.WaitDoc.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail(String email);
}
