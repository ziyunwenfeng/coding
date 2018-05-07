package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutB")
public class FanoutReceiver2 {
	@RabbitHandler
	public void process(String hello){
		System.out.println("FanoutReceiver2");
		System.out.println(hello);
	}
}
