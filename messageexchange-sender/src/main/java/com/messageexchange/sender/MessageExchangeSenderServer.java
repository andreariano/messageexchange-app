package com.messageexchange.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Instantiates a Message Exchange Sender Service and registers itself to an Eureka server previously started on a default port
 * 
 * @author andre.ariano
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class MessageExchangeSenderServer {
	public static final String RECEIVER_SERVICE_URL = "http://MESSAGE-EXCHANGE-RECEIVER-SERVICE";
	
	@LoadBalanced
	@Bean
	protected RestOperations restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MessageExchangeSenderServer.class, args);
	}

	@Bean
	public MessageExchangeSenderController messageExchangeApiController() {
		return new MessageExchangeSenderController(RECEIVER_SERVICE_URL, restTemplate());
	}
}
