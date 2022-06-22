package com.cts.eauction.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class ProductTest extends TestCase {

	@Test
	public void testProductModel() {
		Product product = new Product();
		product.setBidDetails(new ArrayList<>());
		product.setBidEndDate(new Timestamp(0));
		product.setCategory("test");
		product.setDetailedDesc("detail");
		product.setProductName("productName");
		product.setSeller(new Seller());
		product.setShortDesc("short");
		product.setStartingPrice("100.00");
		product.setProductId(1);
		assertNotNull(product.getDetailedDesc());
		assertNotNull(product.getBidDetails());
		assertNotNull(product.getBidEndDate());
		assertNotNull(product.getCategory());
		assertNotNull(product.getDetailedDesc());
		assertNotNull(product.getProductName());
		assertNotNull(product.getSeller());
		assertNotNull(product.getShortDesc());
		assertNotNull(product.getStartingPrice());
		assertNotNull(product.getProductId());
	}
}
