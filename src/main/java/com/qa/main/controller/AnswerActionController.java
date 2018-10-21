package com.qa.main.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.bus.KafkaProducer;
import com.qa.main.model.AnswerAction;
import com.qa.main.repo.AnswerActionRepository;

@RestController
@RequestMapping("/answeractions")
public class AnswerActionController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AnswerActionRepository actionRepo;
	
	@Autowired
	KafkaProducer producer;
	
	@PostMapping("{answerId}")
	public boolean answerQuestion(@PathVariable String answerId, Principal principal) {
		//TODO User authenticated user olcak.
		AnswerAction action = new AnswerAction();
		action.setAnswerId(answerId);
		
		/// TODO convert (OAuth2Authentication)principal).getUserAuthentication().getDetails() to class
		/*Map me = (Map)((Map)((OAuth2Authentication)principal).getUserAuthentication().getDetails()).get("me");
		Map user = (Map)me.get("User");
		action.setUsername(user.get("username").toString());*/
		action.setUsername("sezer");
		actionRepo.save(action);
		producer.sendToKafka(action);
	    rabbitTemplate.convertAndSend(action);	
		return true;
	}
	
	@GetMapping("{answerId}")
	public AnswerAction getAnsweredQuestion(@PathVariable String answerId, Principal principal) {
		AnswerAction action = actionRepo.findByAnswerId(answerId);
		
		return action;
	}
	
	@DeleteMapping("{answerId}")
	public void deleteAnsweredActions(@PathVariable String answerId, Principal principal) {
		actionRepo.deleteByAnswerId(answerId);
	}
	
	
}
