package com.cts.eauction.config;

import org.junit.Test;

import junit.framework.TestCase;

public class KafkaConfigTest extends TestCase {

	@Test
	public void testKafkaConfigTest() {
		KafkaConfig kafkaConfig = new KafkaConfig();
		assertNotNull(kafkaConfig.producerFactory());
		assertNotNull(kafkaConfig.consumerFactory());
		assertNotNull(kafkaConfig.kafkaTemplate());
		assertNotNull(kafkaConfig.concurrentKafkaListenerContainerFactory());
	}

}
