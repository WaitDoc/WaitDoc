package com.team13.WaitDoc.hospital.entity;

import com.team13.WaitDoc.chats.entity.ChatRoom;
import com.team13.WaitDoc.hospital.entity.Hospital;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class HospitalInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Hospital hospital;

    @OneToOne(fetch = LAZY)
    private ChatRoom room;
}
