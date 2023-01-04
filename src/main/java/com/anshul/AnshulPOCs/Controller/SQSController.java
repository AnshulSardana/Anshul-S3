package com.anshul.AnshulPOCs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.AnshulPOCs.Service.SQSService;

@RestController
@RequestMapping("/api/sqs/")
public class SQSController {

	@Autowired
	SQSService sqsService;
	
	@GetMapping("/publishMessage/{message}")
	public ResponseEntity<String> sendMessageToSQSQueue(@PathVariable String message){
		
		return new ResponseEntity<String>(sqsService.sendMessageToSQSQueue(message), HttpStatus.OK);
		
	}
}
