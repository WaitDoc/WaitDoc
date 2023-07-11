package com.team13.WaitDoc.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHospitalMember is a Querydsl query type for HospitalMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHospitalMember extends EntityPathBase<HospitalMember> {

    private static final long serialVersionUID = 1919456280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHospitalMember hospitalMember = new QHospitalMember("hospitalMember");

    public final QHospital hospital;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team13.WaitDoc.member.entity.QMember member;

    public final EnumPath<HospitalMemberRole> role = createEnum("role", HospitalMemberRole.class);

    public QHospitalMember(String variable) {
        this(HospitalMember.class, forVariable(variable), INITS);
    }

    public QHospitalMember(Path<? extends HospitalMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHospitalMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHospitalMember(PathMetadata metadata, PathInits inits) {
        this(HospitalMember.class, metadata, inits);
    }

    public QHospitalMember(Class<? extends HospitalMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new QHospital(forProperty("hospital"), inits.get("hospital")) : null;
        this.member = inits.isInitialized("member") ? new com.team13.WaitDoc.member.entity.QMember(forProperty("member")) : null;
    }

}

