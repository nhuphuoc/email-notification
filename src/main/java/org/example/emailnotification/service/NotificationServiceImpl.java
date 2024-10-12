package org.example.emailnotification.service;

import org.example.emailnotification.entity.EmailDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private EmailServiceImpl emailServiceImpl;
	@Autowired
	NotificationServiceImpl(EmailServiceImpl emailServiceImpl) {
		this.emailServiceImpl = emailServiceImpl;
	}
	public String test(String requestId) {
		LOGGER.info("[Request id {}]: Receive request", requestId);
		return "test";
	}

	public String sendEmail(String email, String requestId, String subject) {
		LOGGER.info("[Request id {}]: Email received: {}", requestId, email);
		EmailDetail emailDetail = new EmailDetail();
		emailDetail.setRecipient(email);
		emailDetail.setSubject(subject);
		emailDetail.setMsgBody("You did it! This is the request id: " + requestId);
		return emailServiceImpl.sendSimpleMail(emailDetail, requestId);
	}
}
