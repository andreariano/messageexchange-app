package com.messageexchange.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class MessageExchangeApiServer {
	public static final String SEND_SERVICE_URL = "http://MESSAGE-EXCHANGE-SENDER-SERVICE";
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MessageExchangeApiServer.class, args);
	}
	
	@Bean
	public MessageExchangeApiController messageExchangeApiController() {
		return new MessageExchangeApiController(SEND_SERVICE_URL);
	}
}
