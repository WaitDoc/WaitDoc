package com.team13.WaitDoc.hospital.repository.Hospital;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.QDepartment;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.team13.WaitDoc.hospital.entity.QHospital.hospital;
@RequiredArgsConstructor
public class HospitalDslRepositoryImpl implements HospitalDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Hospital> search(CategoryRequestDTO requestDTO, Pageable pageable) {
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

        if(requestDTO.getHoliday() != null){
            builder.and(hospital.operatingTime.holidayStartTime.isNotNull()).and(hospital.operatingTime.holidayEndTime.isNotNull());
        }

        if(requestDTO.getWeekend() != null){
            BooleanExpression saturdayOpen = (hospital.operatingTime.satStartTime.isNotNull().and(hospital.operatingTime.satEndTime.isNotNull()));
            BooleanExpression sundayOpen = (hospital.operatingTime.sunStartTime.isNotNull().and(hospital.operatingTime.sunEndTime.isNotNull()));
            builder.and(ExpressionUtils.or(saturdayOpen,sundayOpen));
        }

        if(requestDTO.getAdmission() != null){
            builder.and(hospital.canAdmit.eq(true));
        }

        if(requestDTO.getNight() != null){
            builder.and(hospital.operatingTime.nightDays.isNotEmpty());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>1"+pageable.getOffset());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>2"+pageable.getPageSize());
        List<Hospital> hospitals = jpaQueryFactory.selectFrom(hospital)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), hospitals.size());
        return new PageImpl<>(hospitals.subList(start, end), pageable, hospitals.size());

    }
}
