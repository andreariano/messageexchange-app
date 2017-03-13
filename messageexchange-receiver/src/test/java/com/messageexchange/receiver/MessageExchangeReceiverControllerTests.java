package com.messageexchange.receiver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.messageexchange.receiverdao.InMemoryMessageDao;
import com.messageexchange.receiverdao.MessageDao;

@RunWith(SpringRunner.class)
public class MessageExchangeReceiverControllerTests {
	private MessageExchangeReceiverController subject;
	private MessageDao messageDao;
	
	@Before
	public void beforeTests() {
		//messageDao = Mockito.mock(MessageDao.class);
		
		messageDao = new InMemoryMessageDao();
		subject = new MessageExchangeReceiverController(messageDao);
	}
	
	@Test
	public void canReceiveARequestForSendingAMessage() throws Exception {
		Method receiveMethod = subject.getClass().getDeclaredMethod("receive", String.class);
		RequestMapping annotation = receiveMethod.getAnnotation(RequestMapping.class);
		String valueParam = annotation.value()[0];
		assertThat(valueParam).isEqualTo("/receive/{message}");
	}
	
	@Test
	public void savesMessageToMessageDaoWhenAMessageIsReceived() {
		String message = "some message";
		
		subject.receive(message);
		
		verify(messageDao, times(1)).saveMessage(message);
	}

	@Test
	public void printsMessagesToConsole() throws InterruptedException{
		for (int i = 0; i < 10; i++) {
			subject.receive(Integer.toString(i));
			Thread.sleep(500);
		}
		
		subject.getAllMessages();
	}
}
