package org.example.emailnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EmailNotificationApplication {

		public static void main(String[] args) {
				SpringApplication.run(EmailNotificationApplication.class, args);
		}

}
