package com.team13.WaitDoc.category.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalResponseDTO {
    @JsonProperty("dutyAddr")
    private String addr;

    @JsonProperty("dutyDivNam")
    private String classify;

    @JsonProperty("dutyName")
    private String dutyName;

    @JsonProperty("dutyTel1")
    private String dutyTel1;

    @JsonProperty("wgs84Lat")
    private double wgs84Lat;

    @JsonProperty("wgs84Lon")
    private double wgs84Lon;

    @JsonProperty("hpid")
    private String hpid;
}
