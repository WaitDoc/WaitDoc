package com.team13.WaitDoc.chats.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryMessage is a Querydsl query type for InquiryMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryMessage extends EntityPathBase<InquiryMessage> {

    private static final long serialVersionUID = -254329461L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryMessage inquiryMessage = new QInquiryMessage("inquiryMessage");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final com.team13.WaitDoc.hospital.entity.QHospitalInquiry hospitalInquiry;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team13.WaitDoc.hospital.entity.QHospitalInquiryMember sender;

    public final StringPath senderName = createString("senderName");

    public final EnumPath<InquiryMessageType> type = createEnum("type", InquiryMessageType.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QInquiryMessage(String variable) {
        this(InquiryMessage.class, forVariable(variable), INITS);
    }

    public QInquiryMessage(Path<? extends InquiryMessage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryMessage(PathMetadata metadata, PathInits inits) {
        this(InquiryMessage.class, metadata, inits);
    }

    public QInquiryMessage(Class<? extends InquiryMessage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospitalInquiry = inits.isInitialized("hospitalInquiry") ? new com.team13.WaitDoc.hospital.entity.QHospitalInquiry(forProperty("hospitalInquiry"), inits.get("hospitalInquiry")) : null;
        this.sender = inits.isInitialized("sender") ? new com.team13.WaitDoc.hospital.entity.QHospitalInquiryMember(forProperty("sender"), inits.get("sender")) : null;
    }

}

