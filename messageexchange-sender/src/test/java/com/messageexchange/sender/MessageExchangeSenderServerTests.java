package com.messageexchange.sender;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageExchangeSenderServerTests {
	
	@Autowired
	private MessageExchangeSenderController controller;
	
	@Test
    public void contextLoads() throws Exception {
		Assert.assertNotNull(controller);
    }
	
}
