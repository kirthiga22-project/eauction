package com.cts.eauction.dto;

import org.junit.Test;

import junit.framework.TestCase;

public class SellerDtoTest extends TestCase {
	
	@Test
	public void testSellerDto() {
		SellerDto dto=new SellerDto();
		dto.setAddress("add");
		dto.setCity("city");
		dto.setEmail("email");
		dto.setFirstName("first");
		dto.setLastName("last");
		dto.setPhoneNumber("9898989789");
		dto.setPincode("745567");
		dto.setState("state");
		
		assertNotNull(dto.getAddress());
		assertNotNull(dto.getCity());
		assertNotNull(dto.getEmail());
		assertNotNull(dto.getFirstName());
		assertNotNull(dto.getLastName());
		assertNotNull(dto.getPhoneNumber());
		assertNotNull(dto.getPincode());
		assertNotNull(dto.getState());
		
		
	}
	}
