package com.team13.WaitDoc.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String key, String data) {
		kafkaTemplate.send(topic, key, data);
	}
}