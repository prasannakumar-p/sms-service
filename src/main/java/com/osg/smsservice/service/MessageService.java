package com.osg.smsservice.service;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.osg.smsservice.entity.Message;
import com.osg.smsservice.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	public JavaMailSender emailSender;

	public Object sendSimpleMessage(Message message) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		mimeMessage.setContent(message, "text/html");
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		helper.setFrom("pkumar.itboy@gmail.com");
		helper.setTo("pkumar.itboy@gmail.com");
		helper.setSubject("subject");
		helper.setText(message.getMessage(), true);
		Message mailEntity = new Message();

		try {

			emailSender.send(mimeMessage);

			mailEntity.setMessage(message.getMessage());
			mailEntity.setMobile(message.getMobile());
			mailEntity.setStatus(message.getStatus());

			messageRepository.save(mailEntity);
		} catch (Exception e) {
			throw new SendFailedException(String.format("Invalid Addresses: %s", e));
		}
		return mailEntity;
	}

}
