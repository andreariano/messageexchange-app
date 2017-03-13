package com.messageexchange.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author andre.ariano
 *
 */
@RestController
public class MessageExchangeApiController {
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	protected String sendServiceUrl;
	
	public MessageExchangeApiController(String sendServiceUrl) {
		this.sendServiceUrl = sendServiceUrl.startsWith("http") ? sendServiceUrl 
				: "http://" + sendServiceUrl;
	}
	
	@RequestMapping("/send/{message}")
	public void send(@PathVariable(value="message") String message) {
		restTemplate.getForObject(this.sendServiceUrl + "/send/" + message, String.class);
	}
}
