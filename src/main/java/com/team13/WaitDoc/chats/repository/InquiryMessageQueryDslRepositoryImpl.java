package com.team13.WaitDoc.chats.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InquiryMessageQueryDslRepositoryImpl implements InquiryMessageQueryDslRepository{

    private final JPAQueryFactory queryFactory;
}
