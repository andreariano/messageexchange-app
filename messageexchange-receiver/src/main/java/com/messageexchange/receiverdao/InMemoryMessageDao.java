package com.messageexchange.receiverdao;

import java.util.List;
import java.util.Observable;

import org.apache.coyote.http2.ConnectionException;
import org.springframework.stereotype.Component;

import io.reactivex.internal.operators.observable.ObservableFromArray;

/**
 * Handles the persistence and retrieval of messages in memory
 * 
 * @author andre.ariano
 *
 */
@Component
public class InMemoryMessageDao implements MessageDao {

	private io.reactivex.Observable<List<String>> messages;
	
	public InMemoryMessageDao() {
		messages = ObservableFromArray.empty();
	}
	
	/**
	 * Saves a message into an inMemory list
	 * @param message
	 * @throws ConnectionException
	 */
	public void saveMessage(String message) {
		messages. add(message);
	}

	/**
	 * Get the latest received message 
	 */
	public String getLastMessage() {
		if (messages.isEmpty() != null)
			return "";
		
		return messages.last(null).toString();
	}

	public Observable<String> getAllMessages() {
		return this.messages;
	}

	@Override
	public ObservableFromArray<String> getAllMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
