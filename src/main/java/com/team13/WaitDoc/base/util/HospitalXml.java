package com.team13.WaitDoc.base.util;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team13.WaitDoc.hospital.entity.Hospital;
import lombok.Data;

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
        private String dutyEtc; //비고
        private String dutyDiv; //병원 분류 (A)
        private String dutyDivNam; //병원 분류명 (의원)
        private String dutyEmcls; // 응급 의료기관 코드(G099)
        private String dutyEmclsName; //응급 의료기관 코드명(응급기관 이외)
        private long dutyEryn; //응급실운영여부(1)
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

        private Map<String, Object> additionalProperties = new HashMap<>();

        @JsonAnySetter
        public void setAdditionalProperty(String key, Object value) {
            additionalProperties.put(key, value);
        }

        public Hospital toEntity(){
            return Hospital.builder()
                    .name(dutyName)
                    .addr(dutyAddr)
                    .department(dutyDivNam)
                    .latitude(wgs84Lat)
                    .longitude(wgs84Lon)
                    .hpid(hpid)
                    .monStartTime(dutyTime1s)
                    .monEndTime(dutyTime1c)
                    .tueStartTime(dutyTime2s)
                    .tueEndTime(dutyTime2c)
                    .wedStartTime(dutyTime3s)
                    .wedEndTime(dutyTime3c)
                    .thuStartTime(dutyTime4s)
                    .thuEndTime(dutyTime4c)
                    .friStartTime(dutyTime5s)
                    .friEndTime(dutyTime5c)
                    .satStartTime(dutyTime6s)
                    .satEndTime(dutyTime6c)
                    .sunStartTime(dutyTime7s)
                    .sunEndTime(dutyTime7c)
                    .holidayStartTime(dutyTime8s)
                    .holidayEndTime(dutyTime8c)
                    .build();
        }
    }
}
