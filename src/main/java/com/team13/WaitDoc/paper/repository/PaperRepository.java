package com.team13.WaitDoc.paper.repository;

import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.paper.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaperRepository  extends JpaRepository<Paper,Long> {

}
