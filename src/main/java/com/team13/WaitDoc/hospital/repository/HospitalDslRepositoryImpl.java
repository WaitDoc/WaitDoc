package com.team13.WaitDoc.hospital.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.QDepartment;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static com.team13.WaitDoc.hospital.entity.QHospital.hospital;
@RequiredArgsConstructor
public class HospitalDslRepositoryImpl implements HospitalDslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Hospital> search(CategoryRequestDTO requestDTO) {
        BooleanBuilder builder = new BooleanBuilder();
        if(requestDTO.getAddrs() != null){
            for(String addr:requestDTO.getAddrs()){
                builder.and(hospital.addr.contains(addr));
            }
        }

        if(requestDTO.getClassify() != null){
            builder.and(hospital.classify.contains(requestDTO.getClassify()));
        }

        if(requestDTO.getDepartment() != null){
            QDepartment department = QDepartment.department;
            builder.and(hospital.departments.any().name.contains(requestDTO.getDepartment()));
        }

        if(requestDTO.getName() != null){
            builder.and(hospital.name.contains(requestDTO.getName()));
        }

        List<Hospital> hospitals = jpaQueryFactory.selectFrom(hospital)
                .where(builder)
                .fetch();
        return hospitals;


    }
}