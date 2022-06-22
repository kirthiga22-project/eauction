package com.cts.eauction.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.model.Product;
import com.cts.eauction.util.EauctionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisMessageProcessor {

	private static final Logger LOGGER = LogManager.getLogger(RedisMessageProcessor.class);

	@Autowired
	private RedisTemplate<String, Product> redisTemplate;
	private ValueOperations<String, Product> valueOperations;

	@PostConstruct
	private void init() {
		valueOperations = redisTemplate.opsForValue();
	}

	@KafkaListener(groupId = "group-id-string-1", topics = "productBidUpdate", containerFactory = "kafkaListenerContainerFactory")
	public void receiveMessage(String message) {
		ObjectMapper obj = new ObjectMapper();
		Product product = null;
		try {
			product = obj.readValue(message, Product.class);
			if (null != product) {
				valueOperations.set(product.getProductId().toString(), product);
				LOGGER.info("Message consumed from kafka topic {}",
						valueOperations.get(product.getProductId().toString()));
			}
		} catch (JsonProcessingException e) {
			LOGGER.error("JsonProcessingException while processing json ", e);
			throw new EauctionException("JsonProcessingException while processing json",
					EauctionUtils.setErrorValues(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), Arrays.asList(e.getMessage())));
		}

	}

	public Product retrieveProduct(Integer productId) {
		return valueOperations.get(productId.toString());
	}

}
