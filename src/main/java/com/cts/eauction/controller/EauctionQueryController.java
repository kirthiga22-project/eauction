package com.cts.eauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.service.IEauctionQueryService;

@RestController
@RequestMapping("/e-auction/api/v1")
public class EauctionQueryController {
	
	@Autowired
	private IEauctionQueryService eauctionQueryService;
	
	/**
	 * Method to retrieve bid details
	 * @param productId
	 * @return
	 */
	@CrossOrigin(origins ="http://localhost:4200")
	@GetMapping(path ="/seller/show-bids/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductQueryDto retrieveBidDetails(@PathVariable Integer productId) {
		return eauctionQueryService.retrieveProduct(productId);
	}

}
