package com.example.cloudStream;

import javax.xml.transform.Source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CloudStreamApplication.class)
@WebAppConfiguration
@EnableBinding(CloudStreamApplicationTests.SinkSender.class)
public class CloudStreamApplicationTests {
	@Autowired
	SinkSender sinkSender;
	@Test
	public void test() {
		sinkSender.output().send(MessageBuilder.withPayload("produce a message ：http://blog.didispace.com").build());
	}
	public interface SinkSender {
		String OUTPUT = "input";
		@Output(SinkSender.OUTPUT)
		MessageChannel output();
	}
}
