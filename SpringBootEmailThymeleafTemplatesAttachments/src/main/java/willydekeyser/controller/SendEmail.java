package willydekeyser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import willydekeyser.service.MailSenderService;

@RestController
public class SendEmail {

	private final MailSenderService mailService;

	public SendEmail(MailSenderService mailService) {
		this.mailService = mailService;
	}
	
	@GetMapping("/email")
	public String email() {
		try {
			mailService.sendHtmlMail("spring.boot.programming@gmail.com");
		} catch (MessagingException e) {
			return "Error in sending email: " + e;
		}
		return "E-mail sending.....";
	}
	
}