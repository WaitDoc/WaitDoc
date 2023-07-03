package com.team13.WaitDoc.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHospitalInquiry is a Querydsl query type for HospitalInquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHospitalInquiry extends EntityPathBase<HospitalInquiry> {

    private static final long serialVersionUID = 380481545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHospitalInquiry hospitalInquiry = new QHospitalInquiry("hospitalInquiry");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final QHospital hospital;

    public final SetPath<HospitalInquiryMember, QHospitalInquiryMember> hospitalInquiryMembers = this.<HospitalInquiryMember, QHospitalInquiryMember>createSet("hospitalInquiryMembers", HospitalInquiryMember.class, QHospitalInquiryMember.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QHospitalInquiry(String variable) {
        this(HospitalInquiry.class, forVariable(variable), INITS);
    }

    public QHospitalInquiry(Path<? extends HospitalInquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHospitalInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHospitalInquiry(PathMetadata metadata, PathInits inits) {
        this(HospitalInquiry.class, metadata, inits);
    }

    public QHospitalInquiry(Class<? extends HospitalInquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new QHospital(forProperty("hospital")) : null;
    }

}

