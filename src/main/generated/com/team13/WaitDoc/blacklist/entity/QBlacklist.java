package com.team13.WaitDoc.blacklist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlacklist is a Querydsl query type for Blacklist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlacklist extends EntityPathBase<Blacklist> {

    private static final long serialVersionUID = 521715120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlacklist blacklist = new QBlacklist("blacklist");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final com.team13.WaitDoc.hospital.entity.QHospital hospital;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final com.team13.WaitDoc.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final NumberPath<Integer> warningCount = createNumber("warningCount", Integer.class);

    public QBlacklist(String variable) {
        this(Blacklist.class, forVariable(variable), INITS);
    }

    public QBlacklist(Path<? extends Blacklist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlacklist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlacklist(PathMetadata metadata, PathInits inits) {
        this(Blacklist.class, metadata, inits);
    }

    public QBlacklist(Class<? extends Blacklist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new com.team13.WaitDoc.hospital.entity.QHospital(forProperty("hospital"), inits.get("hospital")) : null;
        this.member = inits.isInitialized("member") ? new com.team13.WaitDoc.member.entity.QMember(forProperty("member")) : null;
    }

}

