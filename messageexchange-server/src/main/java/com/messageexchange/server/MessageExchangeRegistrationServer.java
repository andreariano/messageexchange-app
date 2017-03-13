package com.messageexchange.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Instantiates and Eureka Service Registry at default port (8761)
 * 
 * @author andre.ariano
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class MessageExchangeRegistrationServer {
	
	public static void main(String[] args){
		SpringApplication.run(MessageExchangeRegistrationServer.class, args);
	}
	
}
