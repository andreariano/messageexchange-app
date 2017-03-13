package com.messageexchange.receiverdao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InMemoryMessageDaoTests {
	private InMemoryMessageDao subject = null;
	
	@Before
	public void beforeTests(){
		subject = new InMemoryMessageDao();
	}
	
	@Test
	public void savesReceivedMessageIntoDatabase() {
		String testMessage = "new message";
		
		subject.saveMessage(testMessage);
		
		String savedMessage = subject.getLastMessage();
		
		Assert.assertThat(savedMessage, is(equalTo(testMessage)));
	}
	
	@Test
	public void returnsEmptyStringWhenListContainsNoItems() {
		String message = subject.getLastMessage();
		
		Assert.assertThat(message, is(equalTo("")));
	}
	
	@Test
	public void returnsAllSavedMessages() {
		String message1 = "message 1";
		String message2 = "message 2";
		
		subject.saveMessage(message1);
		subject.saveMessage(message2);
		
		List<String> messages = subject.getAllMessages();
		
		Assert.assertThat(messages.size(), is(equalTo(2)));
		Assert.assertThat(messages.get(0), is(equalTo(message1)));
		Assert.assertThat(messages.get(1), is(equalTo(message2)));
	}
}
