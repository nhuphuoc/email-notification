package org.example.emailnotification.service;

import org.example.emailnotification.entity.EmailDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") private String sender;

	public String sendSimpleMail(EmailDetail detail, String requestId)
	{

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage
							= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(detail.getRecipient());
			mailMessage.setText(detail.getMsgBody());
			mailMessage.setSubject(detail.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			LOGGER.info("[Request id {}]: Email sent successfully", requestId);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			LOGGER.error("[Request id {}]: Email sent failed", requestId);
			return "Error while Sending Mail";
		}
	}
}
