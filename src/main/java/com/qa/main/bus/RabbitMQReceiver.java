package com.qa.main.bus;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.qa.main.model.AnswerAction;

@Component
public class RabbitMQReceiver {
    @RabbitListener(bindings = 
        @QueueBinding(
                value = @Queue(value = "q1", 
                    durable = "true", 
                    autoDelete = "false",
                    ignoreDeclarationExceptions = "true"), 
                exchange = @Exchange(value = "spring-boot-exchange", 
                        type= ExchangeTypes.TOPIC, 
                        durable = "true", 
                        autoDelete = "false",
                        ignoreDeclarationExceptions = "true")
                )
    )
    public void handleMessageHistory(@Payload AnswerAction answerAction) {
       System.out.println(answerAction);
    }

}
