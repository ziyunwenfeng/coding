package com.example.cloudStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
@SpringBootApplication
@EnableBinding(Sink.class)
public class CloudStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamApplication.class, args);
	}
	@StreamListener(Sink.INPUT)
    public void sinkMessage(Object message){
       System.out.println("AAAAAAAAAAAAA: "+message);
    }
	
}
