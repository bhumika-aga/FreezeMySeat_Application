package com.moviebookingapp.FreezeMySeatServer.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

	@KafkaListener(topics = "topic1")
	public void listen(ConsumerRecord<String, String> event) {
		String payload = event.value();
		System.out.println("Consumed Message");
		System.out.println(payload);
	}
}