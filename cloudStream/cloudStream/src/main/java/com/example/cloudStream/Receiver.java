package com.example.cloudStream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
@EnableBinding(Sink.class)
public class Receiver {
	@StreamListener(Sink.INPUT)
    public void sinkMessage(Object message){
       System.out.println("received message: "+message);
    }
}
