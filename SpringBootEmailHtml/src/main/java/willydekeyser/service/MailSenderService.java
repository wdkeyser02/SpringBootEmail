package willydekeyser.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSenderService {

	@Value("${spring.mail.username}")
	private String USERNAME;
	private final JavaMailSender mailSender;

	public MailSenderService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendHtmlMail(String to) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress(USERNAME));
	    message.setRecipients(MimeMessage.RecipientType.TO, to);
	    message.setSubject("HTML email from Spring Boot");

	    String htmlContent = "<h1>This is a HTML Spring Boot email</h1>" +
	                         "<p>It can contain <strong>HTML</strong> content.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
    }
	
}
