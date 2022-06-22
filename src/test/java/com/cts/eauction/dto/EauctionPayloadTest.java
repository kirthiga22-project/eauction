package com.cts.eauction.dto;

import org.junit.Test;

import com.cts.eauction.model.Product;

import junit.framework.TestCase;

public class EauctionPayloadTest extends TestCase {

	@Test
	public void testPayload() {
		EauctionPayload<Product> payload = new EauctionPayload<>();
		payload.setStatus("200");
		payload.setError(null);
		payload.setData(new Product());

		assertEquals("200", payload.getStatus());
		assertNotNull(payload.getData());
		assertNull(payload.getError());
	}
}
