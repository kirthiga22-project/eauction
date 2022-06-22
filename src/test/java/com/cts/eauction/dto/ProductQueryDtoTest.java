package com.cts.eauction.dto;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class ProductQueryDtoTest extends TestCase {

	@Test
	public void testProductQueryDto() {
		ProductQueryDto dto = new ProductQueryDto();
		 dto = ProductQueryDto.builder().bidEndDate("2000-09-09").category("category")
				.startingPrice("100").shortDesc("short").productName("name").productId(1).bidDetails(new ArrayList<>()).detailedDesc("detail").build();
		dto.setBidDetails(new ArrayList<>());
		dto.setBidEndDate("2000-09-09");
		dto.setCategory("category");
		dto.setDetailedDesc("detail");
		dto.setProductId(1);
		dto.setProductName("name");
		dto.setShortDesc("short");
		dto.setStartingPrice("100");
		assertNotNull(dto.getBidEndDate());
		assertNotNull(dto.getCategory());
		assertNotNull(dto.getDetailedDesc());
		assertNotNull(dto.getProductName());
		assertNotNull(dto.getShortDesc());
		assertNotNull(dto.getStartingPrice());
		assertNotNull(dto.getProductId());
		assertNotNull(dto.getBidDetails());
	}
}
