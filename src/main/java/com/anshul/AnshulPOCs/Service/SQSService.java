package com.anshul.AnshulPOCs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SQSService {

	@Value("${aws.sqsendpoint}")
	private String sqsEndpoint;
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	public String sendMessageToSQSQueue(String message) {
	
		queueMessagingTemplate.send(sqsEndpoint, MessageBuilder.withPayload(message).build());
		
		return "Message has been published successfully";
	}
}
