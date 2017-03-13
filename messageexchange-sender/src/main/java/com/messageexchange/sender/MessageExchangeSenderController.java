package com.messageexchange.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

/**
 * 
 * @author andre.ariano
 *
 * Handles sent messages in the message exchange sender service
 */
@RestController
public class MessageExchangeSenderController {
	private RestOperations restTemplate;
	protected String receiverServiceUrl;
	
	public MessageExchangeSenderController(String receiverServiceUrl, @Autowired @LoadBalanced RestOperations restTemplate) {
		this.restTemplate = restTemplate;
		
		this.receiverServiceUrl = receiverServiceUrl.startsWith("http") ? receiverServiceUrl 
				: "http://" + receiverServiceUrl;
	}
	
	/**
	 * Sends a message to the receiver service
	 * @param message
	 */
	@RequestMapping(value = "/send/{message}", method = RequestMethod.GET)
	public void send(@PathVariable(value="message") String message) {
		this.restTemplate.getForObject(this.receiverServiceUrl + "/receive/" + message, String.class);
	}
}
