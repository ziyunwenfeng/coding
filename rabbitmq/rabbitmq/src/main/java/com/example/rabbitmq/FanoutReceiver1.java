package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutA")
public class FanoutReceiver1 {
	@RabbitHandler
	public void process(String hello){
		System.out.println("FanoutReceiver1");
		System.out.println(hello);
	}
}
