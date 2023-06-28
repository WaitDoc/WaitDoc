package com.team13.WaitDoc.waiting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WaitingRequest {
	private Long hospitalId;
	private Long memberId;
	private String name;
	private String type;


}
