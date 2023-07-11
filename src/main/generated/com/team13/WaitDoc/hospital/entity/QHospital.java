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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHospital hospital = new QHospital("hospital");

    public final com.team13.WaitDoc.base.entity.QBaseEntity _super = new com.team13.WaitDoc.base.entity.QBaseEntity(this);

    public final StringPath addr = createString("addr");

    public final NumberPath<Integer> bedCount = createNumber("bedCount", Integer.class);

    public final BooleanPath canAdmit = createBoolean("canAdmit");

    public final StringPath classify = createString("classify");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath department = createString("department");

    public final ListPath<Department, QDepartment> departments = this.<Department, QDepartment>createList("departments", Department.class, QDepartment.class, PathInits.DIRECT2);

    public final BooleanPath hasER = createBoolean("hasER");

    public final SetPath<HospitalInquiry, QHospitalInquiry> hospitalInquiries = this.<HospitalInquiry, QHospitalInquiry>createSet("hospitalInquiries", HospitalInquiry.class, QHospitalInquiry.class, PathInits.DIRECT2);

    public final StringPath hpid = createString("hpid");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath introduction = createString("introduction");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final QOperatingTime operatingTime;

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> waitingNumber = createNumber("waitingNumber", Integer.class);

    public QHospital(String variable) {
        this(Hospital.class, forVariable(variable), INITS);
    }

    public QHospital(Path<? extends Hospital> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHospital(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHospital(PathMetadata metadata, PathInits inits) {
        this(Hospital.class, metadata, inits);
    }

    public QHospital(Class<? extends Hospital> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.operatingTime = inits.isInitialized("operatingTime") ? new QOperatingTime(forProperty("operatingTime"), inits.get("operatingTime")) : null;
    }

}

