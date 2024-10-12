package org.example.emailnotification.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.emailnotification.service.EmailServiceImpl;
import org.example.emailnotification.service.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookLibConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookLibConsumerService.class);

	private EmailServiceImpl emailService;
	private NotificationServiceImpl notificationService;
	@Autowired
	BookLibConsumerService(EmailServiceImpl emailService, NotificationServiceImpl notificationService) {
		this.emailService = emailService;
		this.notificationService = notificationService;
	}

	@KafkaListener(topics = "${booklib.kafka.topic}", groupId = "email-noti-group")
	public void consume(String message) {
		LOGGER.info("Received message: {}", message);
		// Xử lý message tại đây
	}
	@KafkaListener(topics = "${booklib.kafka.email_list}", groupId = "email-noti-group")
	public void consumeWaitingEmailList(String message) {
		LOGGER.info("Received message email: {}", message);
		notificationService.sendEmail(message, UUID.randomUUID().toString().substring(0,5), "Message from kafka");
	}
}
