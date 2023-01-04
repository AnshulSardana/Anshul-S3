package com.anshul.AnshulPOCs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.AnshulPOCs.DTO.MessageDto;
import com.anshul.AnshulPOCs.Service.SNSService;

@RestController
@RequestMapping("/api/sns/")
public class SNSController {

	@Autowired
	SNSService snsService;
	
	@GetMapping("/subscribe/{message}")
	public ResponseEntity<String> subscribeEmailToTopic(@PathVariable String message){
		
		return new ResponseEntity<String>(snsService.subscribeEmailToTopic(message), HttpStatus.OK);
	}

	@PostMapping("/publishMessageToTopic")
	public ResponseEntity<String> subscribeEmailToTopic(@RequestBody MessageDto messageDto){
		
		return new ResponseEntity<String>(snsService.publishMessageToTopic(messageDto), HttpStatus.OK);
	}
}
