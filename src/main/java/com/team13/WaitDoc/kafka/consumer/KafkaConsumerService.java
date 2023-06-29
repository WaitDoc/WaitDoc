package com.team13.WaitDoc.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.team13.WaitDoc.waiting.dto.WaitingInfo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "hospital_waiting", groupId = "myGroup")
	public void consumeWaitingInfo(String waitingInfoJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			WaitingInfo waitingInfo = objectMapper.readValue(waitingInfoJson, WaitingInfo.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}