package com.team13.WaitDoc.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team13.WaitDoc.kafka.producer.KafkaProducerService;

@SpringBootTest
public class KafkaProducerServiceTest {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Test
	public void testSendMessage() {
		String topic = "hospital_waiting";
		String key = "1";
		String data = "{\"member_id\": \"1\"}";

		kafkaProducerService.send(topic, key, data);

	}
}