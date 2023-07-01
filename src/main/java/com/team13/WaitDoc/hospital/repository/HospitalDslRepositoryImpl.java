package com.team13.WaitDoc.hospital.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.team13.WaitDoc.hospital.entity.QHospital.hospital;
@RequiredArgsConstructor
public class HospitalDslRepositoryImpl implements HospitalDslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Hospital> search(CategoryRequestDTO requestDTO) {
        List<Hospital> hospitals = jpaQueryFactory.selectFrom(hospital)
                .where(ExpressionUtils.allOf(addrExpression(requestDTO.getAddrs())))
                .fetch();
        return hospitals;
    }
    private BooleanExpression[] addrExpression(String[] addrs){
        return Arrays.stream(addrs)
                .map(hospital.addr::contains)
                .toArray(BooleanExpression[]::new);
    }
}
