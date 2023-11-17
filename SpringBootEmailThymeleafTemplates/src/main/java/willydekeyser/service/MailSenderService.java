package willydekeyser.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSenderService {

	@Value("${spring.mail.username}")
	private String USERNAME;
	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	public MailSenderService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
	
	public void sendHtmlMail(String to) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		context.setVariable("username", USERNAME);
		String process = templateEngine.process("email_html.html", context);
		MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
		
		helper.setTo(to);
		helper.setFrom(new InternetAddress(USERNAME));
		helper.setSubject("HTML email from Spring Boot");
		helper.setText(process, true);
		
		mailSender.send(message);
		
	}
	
	public void sendTextMail(String to) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		context.setVariable("username", USERNAME);
		String process = templateEngine.process("email_text.txt", context);
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setSubject("TEXT email from Spring Boot");
		helper.setFrom(new InternetAddress(USERNAME));
		helper.setTo(to);
		helper.setText(process);
		mailSender.send(message);
	}
}
