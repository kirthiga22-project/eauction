package com.cts.eauction.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.config.RedisMessageProcessor;
import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.model.BidDetail;
import com.cts.eauction.model.Product;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IEauctionQueryService.class)
public class EauctionQueryServiceTest extends TestCase {

	@Autowired
	private IEauctionQueryService eauctionQueryService;
	
	@MockBean
	private RedisMessageProcessor redisMessageProcessor;
	
	@Test
	public void testRetrieveProduct() {
		Product product = new Product();
		System.out.println(redisMessageProcessor);
		product.setCategory("PAINTING");
		product.setBidEndDate(new Timestamp(new Date().getTime()));
		product.setDetailedDesc("Detail");
		List<BidDetail> list=new ArrayList<>();
		BidDetail dto=new BidDetail();
		dto.setBidAmount("100");
		list.add(dto);
		product.setBidDetails(list);
		Mockito.when(redisMessageProcessor.retrieveProduct(1)).thenReturn(product);
		assertNotNull(eauctionQueryService.retrieveProduct(1));
	}

	@Test(expected = EauctionException.class)
	public void testRetrieveProductException() {
		Mockito.when(redisMessageProcessor.retrieveProduct(Mockito.any())).thenReturn(null);
		eauctionQueryService.retrieveProduct(1);
	}
}
