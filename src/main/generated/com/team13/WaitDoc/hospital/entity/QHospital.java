package com.team13.WaitDoc.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHospital is a Querydsl query type for Hospital
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHospital extends EntityPathBase<Hospital> {

    private static final long serialVersionUID = -1035863074L;

    public static final QHospital hospital = new QHospital("hospital");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    public final StringPath addr = createString("addr");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath department = createString("department");

    public final TimePath<java.time.LocalTime> friEndTime = createTime("friEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> friStartTime = createTime("friStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> holidayEndTime = createTime("holidayEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> holidayStartTime = createTime("holidayStartTime", java.time.LocalTime.class);

    public final SetPath<HospitalInquiry, QHospitalInquiry> hospitalInquiries = this.<HospitalInquiry, QHospitalInquiry>createSet("hospitalInquiries", HospitalInquiry.class, QHospitalInquiry.class, PathInits.DIRECT2);

    public final StringPath hpid = createString("hpid");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath introduction = createString("introduction");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final TimePath<java.time.LocalTime> monEndTime = createTime("monEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> monStartTime = createTime("monStartTime", java.time.LocalTime.class);

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> satEndTime = createTime("satEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> satStartTime = createTime("satStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> sunEndTime = createTime("sunEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> sunStartTime = createTime("sunStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> thuEndTime = createTime("thuEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> thuStartTime = createTime("thuStartTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> tueEndTime = createTime("tueEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> tueStartTime = createTime("tueStartTime", java.time.LocalTime.class);

    public final NumberPath<Integer> waitingNumber = createNumber("waitingNumber", Integer.class);

    public final TimePath<java.time.LocalTime> wedEndTime = createTime("wedEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> wedStartTime = createTime("wedStartTime", java.time.LocalTime.class);

    public QHospital(String variable) {
        super(Hospital.class, forVariable(variable));
    }

    public QHospital(Path<? extends Hospital> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHospital(PathMetadata metadata) {
        super(Hospital.class, metadata);
    }

}

