package com.cts.eauction.service;

import com.cts.eauction.model.BidDetail;
import com.cts.eauction.model.Product;

public interface IEauctionCommandService {

	public Product saveProduct(Product product);

	public void deleteProduct(Integer productId);

	public Product updateBidForProduct(Integer productId, BidDetail bidDetail);

	public Product updateProductBidAmount(Integer productId, String buyerEmailId, String bidAmount);

}
