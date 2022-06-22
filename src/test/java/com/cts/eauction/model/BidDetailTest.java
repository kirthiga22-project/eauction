package com.cts.eauction.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BidDetailTest {
	
	@Test
	public void testBidDetailDto() {
		BidDetail bidModel = new BidDetail();
		bidModel.setAddress("add");
		bidModel.setBidAmount("100");
		bidModel.setCity("city");
		bidModel.setEmail("email");
		bidModel.setFirstName("first");
		bidModel.setLastName("last");
		bidModel.setMobile("9897878678");
		bidModel.setPincode("641035");
		bidModel.setState("state");
		bidModel.setProduct(new Product());
		bidModel.setBidId(1);
		assertEquals("add", bidModel.getAddress());
		assertEquals("100", bidModel.getBidAmount());
		assertEquals("city", bidModel.getCity());
		assertEquals("email", bidModel.getEmail());
		assertEquals("first", bidModel.getFirstName());
		assertEquals("last", bidModel.getLastName());
		assertEquals("9897878678", bidModel.getMobile());
		assertEquals("641035", bidModel.getPincode());
		assertEquals("state", bidModel.getState());
		assertNotNull(bidModel.getProduct());
		assertNotNull(bidModel.getBidId());
	}

}


