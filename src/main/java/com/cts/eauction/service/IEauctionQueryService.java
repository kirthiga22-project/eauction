package com.cts.eauction.service;

import com.cts.eauction.dto.ProductQueryDto;


public interface IEauctionQueryService {
		
	public ProductQueryDto retrieveProduct(Integer productId);

}
