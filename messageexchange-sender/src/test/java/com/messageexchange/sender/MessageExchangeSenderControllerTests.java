package com.messageexchange.sender;

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
import org.springframework.web.client.RestOperations;

@RunWith(SpringRunner.class)
public class MessageExchangeSenderControllerTests {
	private final String URL = "ANY_URL";
	
	private MessageExchangeSenderController subject;
	private RestOperations restOperation;
	
	@Before
	public void beforeTests() {
		restOperation = Mockito.mock(RestOperations.class);
		subject = new MessageExchangeSenderController(URL, restOperation);
	}
	
	@Test
	public void canReceiveARequestForSendingAMessage() throws Exception {
		Method sendMethod = subject.getClass().getDeclaredMethod("send", String.class);
		RequestMapping annotation = sendMethod.getAnnotation(RequestMapping.class);
		String valueParam = annotation.value()[0];
		assertThat(valueParam).isEqualTo("/send/{message}");
	}
	
	@Test
	public void sendsMessageToReceiverService() {
		String message = "some text";
		
		subject.send(message);
		
		verify(restOperation, times(1)).getForObject("http://" + URL + "/receive/" + message, String.class);
	}
}
