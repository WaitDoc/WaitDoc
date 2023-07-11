package com.team13.WaitDoc.paper.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaper is a Querydsl query type for Paper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaper extends EntityPathBase<Paper> {

    private static final long serialVersionUID = 1730599440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaper paper = new QPaper("paper");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imageUrl = createString("imageUrl");

    public final com.team13.WaitDoc.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath title = createString("title");

    public QPaper(String variable) {
        this(Paper.class, forVariable(variable), INITS);
    }

    public QPaper(Path<? extends Paper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaper(PathMetadata metadata, PathInits inits) {
        this(Paper.class, metadata, inits);
    }

    public QPaper(Class<? extends Paper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.team13.WaitDoc.member.entity.QMember(forProperty("member")) : null;
    }

}

