package com.team13.WaitDoc.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHospitalInquiryMember is a Querydsl query type for HospitalInquiryMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHospitalInquiryMember extends EntityPathBase<HospitalInquiryMember> {

    private static final long serialVersionUID = 1323908099L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHospitalInquiryMember hospitalInquiryMember = new QHospitalInquiryMember("hospitalInquiryMember");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QHospitalInquiry hospitalInquiry;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team13.WaitDoc.member.entity.QMember member;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QHospitalInquiryMember(String variable) {
        this(HospitalInquiryMember.class, forVariable(variable), INITS);
    }

    public QHospitalInquiryMember(Path<? extends HospitalInquiryMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHospitalInquiryMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHospitalInquiryMember(PathMetadata metadata, PathInits inits) {
        this(HospitalInquiryMember.class, metadata, inits);
    }

    public QHospitalInquiryMember(Class<? extends HospitalInquiryMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospitalInquiry = inits.isInitialized("hospitalInquiry") ? new QHospitalInquiry(forProperty("hospitalInquiry"), inits.get("hospitalInquiry")) : null;
        this.member = inits.isInitialized("member") ? new com.team13.WaitDoc.member.entity.QMember(forProperty("member")) : null;
    }

}

