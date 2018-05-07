package com.example.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate template ;
	public void send(){
		String context = "hello";
		System.out.println("sender");
//		this.template.convertAndSend("hello",context);
		for(int i=0;i<10;i++){
			this.template.convertAndSend("hello",String.valueOf(i));
		}
	}
	
	//topic exchange
	public void send1(){
		String context = "hello";
		System.out.println("sender1");
		this.template.convertAndSend("exchange","hello1",context);
	}
	public void send2(){
		String context = "hello";
		System.out.println("sender2");
		this.template.convertAndSend("exchange","hello2",context);
	}
	////fanout exchange
	public void send3(){
		String context = "fanout";
		System.out.println("sender3");
		this.template.convertAndSend("fanoutExchange","",context);
	}
}
