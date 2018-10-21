package com.qa.main.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.qa.main.model.AnswerAction;

@Configuration

public class MyKafkaListener {
	

	@Autowired
	KafkaTemplate template;
	
    @KafkaListener(id = "foo", topics = "answered", clientIdPrefix = "myClientId")
	public void listenAnswered(AnswerAction data){
		System.out.println(data);
	}
	
	
}
