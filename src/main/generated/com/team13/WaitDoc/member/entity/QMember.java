package com.team13.WaitDoc.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 308237534L;

    public static final QMember member = new QMember("member1");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    public final StringPath birthday = createString("birthday");

    public final ListPath<com.team13.WaitDoc.blacklist.entity.Blacklist, com.team13.WaitDoc.blacklist.entity.QBlacklist> blacklists = this.<com.team13.WaitDoc.blacklist.entity.Blacklist, com.team13.WaitDoc.blacklist.entity.QBlacklist>createList("blacklists", com.team13.WaitDoc.blacklist.entity.Blacklist.class, com.team13.WaitDoc.blacklist.entity.QBlacklist.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<MemberRole> memberRole = createEnum("memberRole", MemberRole.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final ListPath<com.team13.WaitDoc.waiting.entity.Waiting, com.team13.WaitDoc.waiting.entity.QWaiting> waitings = this.<com.team13.WaitDoc.waiting.entity.Waiting, com.team13.WaitDoc.waiting.entity.QWaiting>createList("waitings", com.team13.WaitDoc.waiting.entity.Waiting.class, com.team13.WaitDoc.waiting.entity.QWaiting.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

