package org.example.emailnotification.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookLibConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookLibConsumerService.class);

	@KafkaListener(topics = "${booklib.kafka.topic}", groupId = "email-noti-group")
	public void consume(String message) {
		LOGGER.info("Received message: {}", message);
		// Xử lý message tại đây
	}
}
