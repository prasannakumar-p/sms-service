package com.osg.smsservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osg.smsservice.entity.Message;
import com.osg.smsservice.service.MessageService;

@RestController
public class MessageController {
	@Autowired
	private MessageService messageService;

	@PostMapping(path = "/mail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sendMail(@RequestBody Message message) {
		Object response = null;
		try {
			response = messageService.sendSimpleMessage(message);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

}
