package com.team13.WaitDoc.category.DTO;

import lombok.*;

import java.util.List;

@Getter
@ToString
//@AllArgsConstructor
public class CategoryRequestDTO {
    private String location;//병원 위치
    private String name;// 병원명
    private String department;//진료과목
    private String classify;//병원 규모
    private Boolean noWaiting;//대기인원이 적은 병원
    private Boolean night;//야간 진료 가능 병원
    private Boolean weekend;//주말 진료 가능 병원
    private Boolean holiday;//공휴일 진료 가능 병원
    private Boolean admission;//입원 가능 병원
    @Setter
    private int page = 0;
    @Setter
    private int rows = 10;

    public String[] getAddrs(){
        if(location != null && !location.isBlank()){
            return location.trim().split(" ");
        }
        return null;
    }

    public CategoryRequestDTO(String location,
                              String name,
                              String department,
                              String classify,
                              Integer page,
                              Integer rows,
                              Boolean noWaiting,
                              Boolean night,
                              Boolean weekend,
                              Boolean holiday,
                              Boolean admission) {
        this.location = location;
        this.name = name;
        this.department = department;
        this.classify = classify;
        this.noWaiting = noWaiting;
        this.night = night;
        this.weekend = weekend;
        this.holiday = holiday;
        this.admission = admission;

        if (page != null && page > 0) this.page = page;
        if (rows != null && rows > 0) this.rows = rows;
    }

}
