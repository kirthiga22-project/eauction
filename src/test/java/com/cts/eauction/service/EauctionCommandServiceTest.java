package com.cts.eauction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.model.BidDetail;
import com.cts.eauction.model.Product;
import com.cts.eauction.repository.EauctionProductCommandRepository;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IEauctionCommandService.class)
public class EauctionCommandServiceTest extends TestCase {

	@Autowired
	private IEauctionCommandService eauctionCommandService;

	@MockBean
	private EauctionProductCommandRepository eauctionProductCommandRepository;

	@Test
	public void testSaveProduct() {
		Mockito.when(eauctionProductCommandRepository.save(Mockito.any())).thenReturn(new Product());
		assertNotNull(eauctionCommandService.saveProduct(new Product()));
	}

	@Test
	public void testDeleteProduct() {
		Mockito.doNothing().when(eauctionProductCommandRepository).deleteById(1);
		eauctionCommandService.deleteProduct(1);
		assertNotNull(eauctionCommandService);
	}

	/**
	 * Method to update bid for product
	 */
	@Test
	public void testUpdateBidForProduct() {
		Product product = new Product();
		product.setBidDetails(new ArrayList<>());
		Optional<Product> optional = Optional.of(product);
		Mockito.when(eauctionProductCommandRepository.findById(1)).thenReturn(optional);
		Mockito.when(eauctionProductCommandRepository.save(product)).thenReturn(product);
		assertNotNull(eauctionCommandService.updateBidForProduct(1, new BidDetail()));
	}

	/**
	 * Method to update product bid amount
	 */
	@Test
	public void testUpdateProductBidAmount() {
		Product product = new Product();
		List<BidDetail> list = new ArrayList<>();
		BidDetail bid = new BidDetail();
		bid.setEmail("email");
		list.add(bid);
		product.setBidDetails(list);
		Optional<Product> optional = Optional.of(product);
		Mockito.when(eauctionProductCommandRepository.findById(1)).thenReturn(optional);
		Mockito.when(eauctionProductCommandRepository.save(product)).thenReturn(product);
		assertNotNull(eauctionCommandService.updateProductBidAmount(1, "email", "100"));
	}
	
	/**
	 * Method to update bid for product
	 */
	@Test(expected = EauctionException.class)
	public void testUpdateBidForProductException() {
		Mockito.when(eauctionProductCommandRepository.findById(1)).thenThrow(EmptyResultDataAccessException.class);
		eauctionCommandService.updateBidForProduct(1, new BidDetail());
	}

	/**
	 * Method to update product bid amount
	 */
	@Test(expected = EauctionException.class)
	public void testUpdateProductBidAmountException() {
		Mockito.when(eauctionProductCommandRepository.findById(1)).thenThrow(EmptyResultDataAccessException.class);
		eauctionCommandService.updateProductBidAmount(1, "email", "100");
	}
}
