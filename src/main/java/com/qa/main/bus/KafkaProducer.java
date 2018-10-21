package com.qa.main.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.qa.main.model.AnswerAction;

//@ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
@Component
public class KafkaProducer {
	
	
	@Autowired
	KafkaTemplate template;
	
	public void sendToKafka(final AnswerAction data) {

	    ListenableFuture<SendResult<Integer, String>> future = template.send("answered", data);
	    future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

	        @Override
	        public void onSuccess(SendResult<Integer, String> result) {
	            System.out.println("handleSucces");
	        }

	        @Override
	        public void onFailure(Throwable ex) {
	        	System.out.println(ex);
	        }
	    });
	}
}
