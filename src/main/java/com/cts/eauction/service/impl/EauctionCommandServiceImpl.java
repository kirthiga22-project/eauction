package com.cts.eauction.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.model.BidDetail;
import com.cts.eauction.model.Product;
import com.cts.eauction.repository.EauctionProductCommandRepository;
import com.cts.eauction.service.IEauctionCommandService;
import com.cts.eauction.util.EauctionUtils;

@Service
public class EauctionCommandServiceImpl implements IEauctionCommandService {

	private static final Logger LOGGER = LogManager.getLogger(EauctionCommandServiceImpl.class);

	@Autowired
	private EauctionProductCommandRepository eauctionProductCommandRepository;

	@Override
	public Product saveProduct(Product product) {
		return eauctionProductCommandRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer productId) {
		eauctionProductCommandRepository.deleteById(productId);
	}

	/**
	 * Method to update bid for product
	 */
	@Override
	public Product updateBidForProduct(Integer productId, BidDetail bidDetail) {
		Product product = null;
		try {
			Optional<Product> optionalProduct = eauctionProductCommandRepository.findById(productId);
			if (optionalProduct.isPresent()) {
				product = optionalProduct.get();
				bidDetail.setProduct(optionalProduct.get());
				product.getBidDetails().add(bidDetail);
				product = eauctionProductCommandRepository.save(product);
			}
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception while updating bid for product ", e);
			throw new EauctionException("Exception while updating bid for product",
					EauctionUtils.setErrorValues(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), Arrays.asList(e.getMessage())));
		}
		return product;
	}

	/**
	 * Method to update product bid amount
	 */
	@Override
	public Product updateProductBidAmount(Integer productId, String buyerEmailId, String bidAmount) {
		Product product = null;
		try {
			Optional<Product> optionalProduct = eauctionProductCommandRepository.findById(productId);
			if (optionalProduct.isPresent()) {
				product = optionalProduct.get();
				product.getBidDetails().stream()
						.filter(bidDetail -> bidDetail.getEmail().equalsIgnoreCase(buyerEmailId))
						.forEach(filteredValue -> filteredValue.setBidAmount(bidAmount));
				product = eauctionProductCommandRepository.save(product);
			}
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception while updating bid for product ", e);
			throw new EauctionException("Exception while updating bid for product",
					EauctionUtils.setErrorValues(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), Arrays.asList(e.getMessage())));
		}
		return product;
	}

}
