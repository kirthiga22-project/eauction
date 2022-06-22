package com.cts.eauction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.model.Product;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RedisMessageProcessor.class)
public class RedisMessageProcessorTest extends TestCase {

	@Mock
	RedisMessageProcessor redisMessageProcessor;

	@MockBean
	private RedisTemplate<String, Product> redisTemplate;

	@MockBean
	private ValueOperations<String, ProductQueryDto> valueOperations;

	@Test
	public void testReceiveMessage() {
		redisMessageProcessor.receiveMessage("{\"message\":\"test\"}");
		assertNotNull(redisMessageProcessor);
	}

	

	
}
