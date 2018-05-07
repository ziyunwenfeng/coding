package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
@RabbitListener(queues = "hello")
public class Receiver2 {
	@RabbitHandler
	public void process(String hello){
		System.out.println("Receiver2");
		System.out.println(hello);
	}
}
