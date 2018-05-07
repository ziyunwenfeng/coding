package com.example.streaminput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
@EnableBinding(Sink.class)
@SpringBootApplication
public class StreaminputApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreaminputApplication.class, args);
	}
	@StreamListener(Sink.INPUT)
    public void transform(Object obj) {
        System.out.println("receive**************"+obj.getClass().getName());
        
    }
}
