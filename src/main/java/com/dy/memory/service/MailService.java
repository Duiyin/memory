package com.dy.memory.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	private final String smtp_server = "smtp.163.com";
	private final String smtp_account = "15220061238@163.com";
	private final String smtp_password = "hjf0419";

	public void send(String toEmail, String toName, String fromName, String subject, String htmlbody) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject(subject);
				helper.setFrom(smtp_account, fromName);
				helper.setTo(toEmail);

				helper.setText(htmlbody, true);
			}
		};

		try {
			javaMailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
