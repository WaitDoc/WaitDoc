package com.team13.WaitDoc.base.util;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team13.WaitDoc.hospital.entity.Department;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.HospitalDepartment;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.service.DepartmentService;
import com.team13.WaitDoc.hospital.service.HospitalService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalXml {
    @Data
    static class Response{
        private Header header;
        private Body body;
        private String cmmMsgHeader;

    }
    @Data
    static class Header{
        public String resultCode;
        public String resultMsg;
    }
    @Data
    static class Body{
        public List<Item> items; //실제 데이터
        public int numOfRows; //페이지 당 데이터 개수
        public int pageNo; //페이지 번호
        public int totalCount; // 데이터 개수
    }
    @Data
    static public class Item{
        private String dutyAddr; //주소 (서울 특별시 서대문구...)
        private String dgidIdName; //병원 진료 과목
        private int dutyEryn; //응급실 운영여부 (1:운영, 2:그외)
        private int dutyHayn; //입원 여부 (1:운영, 2:그외)
        private int dutyHano; //병상 개수

        private String dutyDiv; //병원 분류 (A)
        private String dutyDivNam; //병원 분류명 (의원)
        private String dutyEmcls; // 응급 의료기관 코드(G099)
        private String dutyEmclsName; //응급 의료기관 코드명(응급기관 이외)
        private String dutyEtc; //비고
        private String dutyName;//기관명 (삼성병원)
        private String dutyTel1;//대표전화1
        private int dutyTime1c;//진료종료시간:월 (1530)
        private int dutyTime1s;//진료시작시간:월 (0830)
        private int dutyTime2c;//종료: 화
        private int dutyTime2s;//시작: 화
        private int dutyTime3c;//종료: 수
        private int dutyTime3s;//시작: 수
        private int dutyTime4c;//종료: 목
        private int dutyTime4s;//시작: 목
        private int dutyTime5c;//종료: 금
        private int dutyTime5s;//시작: 금
        private int dutyTime6c;//종료: 토
        private int dutyTime6s;//시작: 토
        private int dutyTime7s;//시작: 일
        private int dutyTime7c;//종료: 일
        private int dutyTime8s;//시작: 공휴일
        private int dutyTime8c;//종료: 공휴일

        private String hpid;//기관 ID(A0000028)
        private int postCdn1;//우편번호1(135)
        private int postCdn2;//우편번호2(750)
        private int rnum;//item 순서 번호(1)
        private double wgs84Lat;//병원위도(37.48813256)
        private double wgs84Lon;//병원경도(127.0851566)
        private String dutyInf;//병원정보

        private Map<String, Object> additionalProperties = new HashMap<>();

        @JsonAnySetter
        public void setAdditionalProperty(String key, Object value) {
            additionalProperties.put(key, value);
        }
        public LocalTime intToTime(int time){
            int hour = time/100 > 23? time/100%24 : time/100;
            if(time == 0)
                return null;
            return LocalTime.of(hour, time % 100);

        }

        public Hospital toHospitalEntity(){
            return Hospital.builder()
                    .name(dutyName)
                    .addr(dutyAddr)
                    .department(dgidIdName)
                    .classify(dutyDivNam)
                    .latitude(wgs84Lat)
                    .longitude(wgs84Lon)
                    .hpid(hpid)
                    .tel(dutyTel1)
                    .introduction(dutyInf)
                    .monStartTime(intToTime(dutyTime1s))
                    .monEndTime(intToTime(dutyTime1c))
                    .tueStartTime(intToTime(dutyTime2s))
                    .tueEndTime(intToTime(dutyTime2c))
                    .wedStartTime(intToTime(dutyTime3s))
                    .wedEndTime(intToTime(dutyTime3c))
                    .thuStartTime(intToTime(dutyTime4s))
                    .thuEndTime(intToTime(dutyTime4c))
                    .friStartTime(intToTime(dutyTime5s))
                    .friEndTime(intToTime(dutyTime5c))
                    .satStartTime(intToTime(dutyTime6s))
                    .satEndTime(intToTime(dutyTime6c))
                    .sunStartTime(intToTime(dutyTime7s))
                    .sunEndTime(intToTime(dutyTime7c))
                    .holidayStartTime(intToTime(dutyTime8s))
                    .holidayEndTime(intToTime(dutyTime8c))
                    .build();
        }
    }

}