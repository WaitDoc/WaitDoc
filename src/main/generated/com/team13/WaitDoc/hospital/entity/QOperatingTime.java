package com.team13.WaitDoc.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOperatingTime is a Querydsl query type for OperatingTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOperatingTime extends EntityPathBase<OperatingTime> {

    private static final long serialVersionUID = -1455817590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOperatingTime operatingTime = new QOperatingTime("operatingTime");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final TimePath<java.time.LocalTime> friEndTime = createTime("friEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> friStartTime = createTime("friStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> holidayEndTime = createTime("holidayEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> holidayStartTime = createTime("holidayStartTime", java.time.LocalTime.class);

    public final QHospital hospital;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final TimePath<java.time.LocalTime> monEndTime = createTime("monEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> monStartTime = createTime("monStartTime", java.time.LocalTime.class);

    public final StringPath nightDays = createString("nightDays");

    public final TimePath<java.time.LocalTime> satEndTime = createTime("satEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> satStartTime = createTime("satStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> sunEndTime = createTime("sunEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> sunStartTime = createTime("sunStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> thuEndTime = createTime("thuEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> thuStartTime = createTime("thuStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> tueEndTime = createTime("tueEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> tueStartTime = createTime("tueStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> wedEndTime = createTime("wedEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> wedStartTime = createTime("wedStartTime", java.time.LocalTime.class);

    public QOperatingTime(String variable) {
        this(OperatingTime.class, forVariable(variable), INITS);
    }

    public QOperatingTime(Path<? extends OperatingTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOperatingTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOperatingTime(PathMetadata metadata, PathInits inits) {
        this(OperatingTime.class, metadata, inits);
    }

    public QOperatingTime(Class<? extends OperatingTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new QHospital(forProperty("hospital"), inits.get("hospital")) : null;
    }

}

