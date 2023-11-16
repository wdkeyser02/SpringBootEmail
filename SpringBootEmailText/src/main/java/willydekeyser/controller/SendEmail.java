package willydekeyser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import willydekeyser.service.MailSenderService;

@RestController
public class SendEmail {

	private final MailSenderService mailService;

	public SendEmail(MailSenderService mailService) {
		this.mailService = mailService;
	}
	
	@GetMapping("/email")
	public String email() {
		mailService.sendNewMail("spring.boot.programming@gmail.com", "Spring Boot E-mail", "Spring Boot E-mail!");
		return "E-mail sending.....";
	}
	
}
