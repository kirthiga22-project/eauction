package com.cts.eauction.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class BidDetailDtoTest extends TestCase {

	@Test
	public void testBidDetailDto() {
		BidDetailDto dto = new BidDetailDto();
		dto.setAddress("add");
		dto.setBidAmount(Double.valueOf(100));
		dto.setCity("city");
		dto.setEmail("email");
		dto.setFirstName("first");
		dto.setLastName("last");
		dto.setMobile("9897878678");
		dto.setPincode("641035");
		dto.setState("state");
		assertEquals("add", dto.getAddress());
		assertEquals(Double.valueOf(100), dto.getBidAmount());
		assertEquals("city", dto.getCity());
		assertEquals("email", dto.getEmail());
		assertEquals("first", dto.getFirstName());
		assertEquals("last", dto.getLastName());
		assertEquals("9897878678", dto.getMobile());
		assertEquals("641035", dto.getPincode());
		assertEquals("state", dto.getState());
	}

}
