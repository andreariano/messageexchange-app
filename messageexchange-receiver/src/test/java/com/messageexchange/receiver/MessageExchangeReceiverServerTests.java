package com.messageexchange.receiver;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageExchangeReceiverServerTests {
	
	@Autowired
	private MessageExchangeReceiverController controller;
	
	@Test
    public void contextLoads() throws Exception {
		Assert.assertNotNull(controller);
    }
	
}