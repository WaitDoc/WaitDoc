package com.team13.WaitDoc.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HospitalResponseDTO {
    private Long id;
    private String name;

    private String addr;

    private String tel;

    private String classify;

    private String[] department;

    private String info;

    private double wgs84Lat;

    private double wgs84Lon;

    @JsonProperty("hpid")
    private String hpid;
}
