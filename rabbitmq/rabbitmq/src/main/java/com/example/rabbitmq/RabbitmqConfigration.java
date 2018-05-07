package com.example.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfigration {
	@Bean
	public Queue helloQueue(){
		return new Queue("hello");
	}
	// topic exchange
	@Bean
	public Queue helloQueue2(){
		return new Queue("hello2");
	}
	@Bean
	TopicExchange exchange(){
		return new TopicExchange("exchange");
	}
	@Bean
	Binding bindExchangeHello(Queue helloQueue2,TopicExchange exchange){
		return BindingBuilder.bind(helloQueue2).to(exchange).with("hello1");
	}
	@Bean
	Binding bindExchangeHello2(Queue helloQueue,TopicExchange exchange){
		return BindingBuilder.bind(helloQueue).to(exchange).with("hello2");
	}
	//fanoutexchange
	@Bean
	public Queue AMessage(){
		return new Queue("fanoutA");
	}
	@Bean
	public Queue BMessage(){
		return new Queue("fanoutB");
	}
	@Bean
	public Queue CMessage(){
		return new Queue("fanoutC");
	}
	@Bean
	FanoutExchange fanoutExchangeexchange(){
		return new FanoutExchange("fanoutExchange");
	}
	@Bean
	Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange){
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}
	@Bean
	Binding bindingExchangeB(Queue BMessage,FanoutExchange fanoutExchange){
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}
	@Bean
	Binding bindingExchangeC(Queue CMessage,FanoutExchange fanoutExchange){
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}
}
