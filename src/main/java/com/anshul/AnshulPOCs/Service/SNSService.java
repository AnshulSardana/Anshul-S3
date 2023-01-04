package com.anshul.AnshulPOCs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.anshul.AnshulPOCs.DTO.MessageDto;

@Service
public class SNSService {

	@Autowired
	private AmazonSNSClient amazonSNSClient;

	@Value("${aws.topicarn}")
	private String topicARN;

	public String subscribeEmailToTopic(String message) {

		SubscribeRequest subscribeRequest = new SubscribeRequest(topicARN, "email", message);
		amazonSNSClient.subscribe(subscribeRequest);

		return "Email: " + message + " has been subscribed successfully";
	}

	public String publishMessageToTopic(MessageDto messageDto) {

		PublishRequest publishRequest = new PublishRequest(topicARN, messageDto.getMessage(), messageDto.getSubject());
		amazonSNSClient.publish(publishRequest);
		
		return "Message has been published successfully";
	}
}
