package com.team13.WaitDoc.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team13.WaitDoc.base.util.LocationDistance;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
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

    private String[] nightDays;

    private boolean holiday;

    private boolean saturday;

    private boolean sunday;

    private boolean canAdmit;

    private Long distance;

    @JsonProperty("hpid")
    private String hpid;

    public HospitalResponseDTO setDistance(CategoryRequestDTO requestDTO){
        if(requestDTO.getLatitude() != null && requestDTO.getLongitude() != null
            && wgs84Lat > 0 && wgs84Lon > 0){
            this.distance = LocationDistance.calc(requestDTO.getLatitude(), requestDTO.getLongitude(),
                    wgs84Lat, wgs84Lon);
        }
        return this;
    }
}
