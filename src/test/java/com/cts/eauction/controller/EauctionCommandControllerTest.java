package com.cts.eauction.controller;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.dto.BidDetailDto;
import com.cts.eauction.dto.ProductCommandDto;
import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.dto.SellerDto;
import com.cts.eauction.model.Product;
import com.cts.eauction.service.IEauctionCommandService;
import com.cts.eauction.service.IEauctionQueryService;
import com.cts.eauction.validation.EauctionValidator;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EauctionCommandController.class)
public class EauctionCommandControllerTest extends TestCase{

	@MockBean
	private IEauctionCommandService eauctionCommandService;

	@MockBean
	private IEauctionQueryService eauctionQueryService;

	@MockBean
	KafkaTemplate<String, Product> kafkaJsonTemplate;

	@MockBean
	EauctionValidator validator;
	
	@Autowired
	EauctionCommandController controller;



	
	@Test
	public void testSaveProduct() {
		ProductCommandDto productCommandDto = new ProductCommandDto();
		productCommandDto.setDetailedDesc("Detail");
		productCommandDto.setSeller(new SellerDto());
		productCommandDto.setBidDetails(new ArrayList<>());
		Product product = new Product();
		product.setDetailedDesc("Detail");
		Mockito.when(eauctionCommandService.saveProduct(Mockito.any())).thenReturn(product);
		assertNotNull(controller.saveProduct(productCommandDto));
	}
	
	@Test
	public void testDeleteProduct() {
		ProductQueryDto productQueryDto = new ProductQueryDto(); 
		Mockito.when(eauctionQueryService.retrieveProduct(Mockito.any())).thenReturn(productQueryDto);
		Mockito.doNothing().when(validator).validateProductBeforeDelete(productQueryDto);
		Mockito.doNothing().when(eauctionCommandService).deleteProduct(Mockito.any());
		assertNotNull(controller.deleteProduct(1));
	}

	@Test
	public void testUpdateProductBid() {
		ProductQueryDto productQueryDto = new ProductQueryDto(); 
		Mockito.when(eauctionQueryService.retrieveProduct(Mockito.any())).thenReturn(productQueryDto);
			
		Product savedProduct = new Product(); 
		Mockito.when(eauctionCommandService.updateBidForProduct(Mockito.any(),Mockito.any())).thenReturn(savedProduct);
		assertNotNull(controller.updateProductBid(1, new BidDetailDto()));
	}

	/**
	 * Method to update product bid amount
	 * @param productId
	 * @param buyerEmailId
	 * @param newBidAmount
	 * @return
	 */
	@Test
	public void testUpdateProductBidAmount() {
		
		ProductQueryDto productQueryDto = new ProductQueryDto();
		productQueryDto.setBidDetails(new ArrayList<>());
		Mockito.when(eauctionQueryService.retrieveProduct(Mockito.any())).thenReturn(productQueryDto);
		Product savedProduct = new Product(); 
		Mockito.when(eauctionCommandService.updateProductBidAmount(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(savedProduct);
		assertNotNull(controller.updateProductBidAmount(1, "email", "100"));
	}


}
