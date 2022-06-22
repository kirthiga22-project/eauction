package com.cts.eauction.config;

import org.junit.Test;

import junit.framework.TestCase;

public class RedisConfigTest extends TestCase {

	@Test
	public void testRedisConfig() {
		RedisConfig config = new RedisConfig();
		assertNotNull(config.redisConnectionFactory());
		assertNotNull(config.redisTemplate());

	}
}
