package com.cts.eauction.model;

import junit.framework.TestCase;

public class SellerTest extends TestCase {
	public void testSellerDto() {
		Seller seller=new Seller();
		seller.setAddress("add");
		seller.setCity("city");
		seller.setEmail("email");
		seller.setFirstName("first");
		seller.setLastName("last");
		seller.setPhoneNumber("9898989789");
		seller.setPincode("745567");
		seller.setState("state");
		seller.setSellerId(1);
		seller.setProduct(new Product());
		
		assertNotNull(seller.getProduct());
		assertNotNull(seller.getSellerId());
		assertNotNull(seller.getAddress());
		assertNotNull(seller.getCity());
		assertNotNull(seller.getEmail());
		assertNotNull(seller.getFirstName());
		assertNotNull(seller.getLastName());
		assertNotNull(seller.getPhoneNumber());
		assertNotNull(seller.getPincode());
		assertNotNull(seller.getState());
		
		
	}}
