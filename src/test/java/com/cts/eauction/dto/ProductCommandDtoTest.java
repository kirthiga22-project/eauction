package com.cts.eauction.dto;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class ProductCommandDtoTest extends TestCase {

	@Test
	public void testProductCommandDto() {
		ProductCommandDto dto = new ProductCommandDto();
		dto.setBidDetails(new ArrayList<>());
		dto.setBidEndDate(new Date(0));
		dto.setCategory(Category.ORNAMENT);
		dto.setDetailedDesc("detail");
		dto.setProductName("productName");
		dto.setSeller(new SellerDto());
		dto.setShortDesc("short");
		dto.setStartingPrice(100.00);
		assertNotNull(dto.getDetailedDesc());
		assertNotNull(dto.getBidDetails());
		assertNotNull(dto.getBidEndDate());
		assertNotNull(dto.getCategory());
		assertNotNull(dto.getDetailedDesc());
		assertNotNull(dto.getProductName());
		assertNotNull(dto.getSeller());
		assertNotNull(dto.getShortDesc());
		assertNotNull(dto.getStartingPrice());
	}
}
