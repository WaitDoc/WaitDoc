package com.team13.WaitDoc.Category.DTO;

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
    private String name;

    @JsonProperty("dutyTel1")
    private String tel;

    @JsonProperty("wgs84Lat")
    private double latitude;

    @JsonProperty("wgs84Lon")
    private double longitude;

    @JsonProperty("hpid")
    private String hpid;
}
