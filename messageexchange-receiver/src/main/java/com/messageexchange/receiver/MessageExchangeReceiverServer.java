package com.messageexchange.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.messageexchange.receiverdao.InMemoryMessageDao;
import com.messageexchange.receiverdao.MessageDao;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class MessageExchangeReceiverServer {

	public static void main(String[] args) {
		SpringApplication.run(MessageExchangeReceiverServer.class, args);
	}

}
