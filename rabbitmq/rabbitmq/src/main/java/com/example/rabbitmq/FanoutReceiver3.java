package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutC")
public class FanoutReceiver3 {
	@RabbitHandler
	public void process(String hello){
		System.out.println("FanoutReceiver3");
		System.out.println(hello);
	}
}
