package com.messageexchange.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.messageexchange.receiverdao.MessageDao;

import io.reactivex.Observable;

/**
 * Handles sent messages in the message exchange sender service
 * @author andre.ariano
 */
@RestController
@ComponentScan("com.messageexchange.receiverdao")
public class MessageExchangeReceiverController {

	private MessageDao messageDao;
	
	public MessageExchangeReceiverController(@Autowired MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	/**
	 * Persists the received message 
	 * @param message
	 */
	@RequestMapping(value = "/receive/{message}", method = RequestMethod.GET)
	public void receive(@PathVariable("message") String message) {
		messageDao.saveMessage(message);
		
		//System.out.println(messageDao.getLastMessage());
	}
	
	public void getAllMessages(){
		Observable.fromArray(messageDao.getAllMessages())
		.zipWith(
				Observable.range(1, Integer.MAX_VALUE), 
				(string, count) -> String.format("%2d. %s", count, string))
		.subscribe(System.out::println);
	}
}
