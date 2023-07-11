package com.team13.WaitDoc.paper.entity;

import com.team13.WaitDoc.base.entity.BaseEntity;
import com.team13.WaitDoc.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paper extends BaseEntity {

    private String title;

    private String content;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;



}
