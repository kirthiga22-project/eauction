package com.cts.eauction.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.service.IEauctionQueryService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EauctionQueryController.class)
public class EauctionQueryControllerTest extends TestCase {

	@MockBean
	private IEauctionQueryService eauctionQueryService;

	@Autowired
	EauctionQueryController controller;

	@Test
	public void testSaveProduct() {
		ProductQueryDto product = new ProductQueryDto();
		product.setDetailedDesc("Detail");
		Mockito.when(eauctionQueryService.retrieveProduct(Mockito.any())).thenReturn(product);
		assertNotNull(controller.retrieveBidDetails(1));
	}

}
