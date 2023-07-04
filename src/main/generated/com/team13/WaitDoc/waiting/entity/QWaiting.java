package com.team13.WaitDoc.waiting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWaiting is a Querydsl query type for Waiting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWaiting extends EntityPathBase<Waiting> {

    private static final long serialVersionUID = 499007408L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWaiting waiting = new QWaiting("waiting");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final com.team13.WaitDoc.hospital.entity.QHospital hospital;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final com.team13.WaitDoc.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public QWaiting(String variable) {
        this(Waiting.class, forVariable(variable), INITS);
    }

    public QWaiting(Path<? extends Waiting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWaiting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWaiting(PathMetadata metadata, PathInits inits) {
        this(Waiting.class, metadata, inits);
    }

    public QWaiting(Class<? extends Waiting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new com.team13.WaitDoc.hospital.entity.QHospital(forProperty("hospital")) : null;
        this.member = inits.isInitialized("member") ? new com.team13.WaitDoc.member.entity.QMember(forProperty("member")) : null;
    }

}

