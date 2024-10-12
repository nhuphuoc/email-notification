package org.example.emailnotification.controller;

import jakarta.validation.constraints.Email;
import org.example.emailnotification.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/email", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class NotificationController {
	private NotificationServiceImpl emailService;

	private JavaMailSender mailSender;

	@Autowired
	NotificationController(NotificationServiceImpl emailService, JavaMailSender mailSender) {
			this.emailService = emailService;
		this.mailSender = mailSender;
	}

	@GetMapping("/test/{requestId}")
	public ResponseEntity<String> testAPI(@PathVariable String requestId) {
			return new ResponseEntity<>(emailService.test(requestId), HttpStatus.OK);
	}

  @GetMapping("/{email}/{requestId}")
  public ResponseEntity<String> sendEmail(
      @PathVariable String requestId,
      @PathVariable @Email(message = "Invalid email format") String email,
      @RequestParam(value="subject", required=true) String subject) {
    return new ResponseEntity<>(emailService.sendEmail(email, requestId, subject), HttpStatus.OK);
  }
}
