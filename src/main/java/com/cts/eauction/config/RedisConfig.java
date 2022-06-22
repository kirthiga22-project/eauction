package com.cts.eauction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.cts.eauction.model.Product;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, Product> redisTemplate(){
		RedisTemplate<String, Product> productTemplate = new RedisTemplate<>();
		productTemplate.setConnectionFactory(redisConnectionFactory());
		return productTemplate;
	}

}
