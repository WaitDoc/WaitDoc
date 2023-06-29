package com.team13.WaitDoc.waiting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaitingInfo {

	@JsonProperty("hospitalId")
	private Long hospitalId;

	@JsonProperty("memberId")
	private Long memberId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	public WaitingInfo(Long memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}


}
