package com.cts.eauction.controller;

import javax.validation.Valid;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.eauction.dto.BidDetailDto;
import com.cts.eauction.dto.EauctionPayload;
import com.cts.eauction.dto.ProductCommandDto;
import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.model.BidDetail;
import com.cts.eauction.model.Product;
import com.cts.eauction.service.IEauctionCommandService;
import com.cts.eauction.service.IEauctionQueryService;
import com.cts.eauction.validation.EauctionValidator;

@RestController
@RequestMapping("/e-auction/api/v1")
public class EauctionCommandController {

	private static final String PRODUCT_BID_UPDATE = "productBidUpdate";

	private static final String SUCCESS = "Success";

	@Autowired
	private IEauctionCommandService eauctionCommandService;

	@Autowired
	private IEauctionQueryService eauctionQueryService;

	@Autowired
	KafkaTemplate<String, Product> kafkaJsonTemplate;

	@Autowired
	EauctionValidator validator;

	/**
	 * Method to save product
	 * @param productCommandDto
	 * @return
	 */
	@PostMapping(path = "/seller/add-product", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EauctionPayload<Product>> saveProduct(
			@Valid @RequestBody ProductCommandDto productCommandDto) {
		validator.validateProduct(productCommandDto);
		DozerBeanMapper beanMapper = new DozerBeanMapper();
		Product product = beanMapper.map(productCommandDto, Product.class);
		product.getSeller().setProduct(product);
		product.getBidDetails().stream().forEach(bidDetail -> bidDetail.setProduct(product));
		Product savedProduct = eauctionCommandService.saveProduct(product);
		kafkaJsonTemplate.send(PRODUCT_BID_UPDATE, savedProduct);
		EauctionPayload<Product> payLoad = new EauctionPayload<>();
		payLoad.setData(product);
		payLoad.setStatus(SUCCESS);
		return new ResponseEntity<>(payLoad, HttpStatus.OK);
	}

	/**
	 * Method to delete product
	 * @param productId
	 * @return
	 */
	@DeleteMapping(path = "/seller/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
		ProductQueryDto productQueryDto = eauctionQueryService.retrieveProduct(productId);
		validator.validateProductBeforeDelete(productQueryDto);
		eauctionCommandService.deleteProduct(productId);
		return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
	}

	/**
	 * Method to place bid for the given product id
	 * 
	 * @param productId
	 * @param bidDetail
	 * @return
	 */
	@PutMapping(path = "/buyer/place-bid/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EauctionPayload<Product>> updateProductBid(@PathVariable Integer productId,
			@Valid @RequestBody BidDetailDto bidDetailDto) {
		ProductQueryDto productQueryDto = eauctionQueryService.retrieveProduct(productId);
		validator.validateProductForBidding(productQueryDto, bidDetailDto.getEmail(), false);
		DozerBeanMapper beanMapper = new DozerBeanMapper();
		BidDetail bidDetail = beanMapper.map(bidDetailDto, BidDetail.class);
		Product savedProduct = eauctionCommandService.updateBidForProduct(productId, bidDetail);
		kafkaJsonTemplate.send(PRODUCT_BID_UPDATE, savedProduct);
		EauctionPayload<Product> payLoad = new EauctionPayload<>();
		payLoad.setData(savedProduct);
		payLoad.setStatus(SUCCESS);
		return new ResponseEntity<>(payLoad, HttpStatus.OK);
	}

	/**
	 * Method to update product bid amount
	 * @param productId
	 * @param buyerEmailId
	 * @param newBidAmount
	 * @return
	 */
	@PutMapping(path = "/buyer/update-bid/{productId}/{buyerEmailId}/{newBidAmount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EauctionPayload<Product>> updateProductBidAmount(@PathVariable Integer productId,
			@PathVariable String buyerEmailId, @PathVariable String newBidAmount) {
		ProductQueryDto productQueryDto = eauctionQueryService.retrieveProduct(productId);
		validator.validateProductForBidding(productQueryDto, buyerEmailId, true);
		Product product = eauctionCommandService.updateProductBidAmount(productId, buyerEmailId, newBidAmount);
		kafkaJsonTemplate.send(PRODUCT_BID_UPDATE, product);
		EauctionPayload<Product> payLoad = new EauctionPayload<>();
		payLoad.setData(product);
		payLoad.setStatus(SUCCESS);
		return new ResponseEntity<>(payLoad, HttpStatus.OK);

	}
}
