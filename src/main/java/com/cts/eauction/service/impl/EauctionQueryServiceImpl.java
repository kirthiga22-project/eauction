package com.cts.eauction.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.eauction.config.RedisMessageProcessor;
import com.cts.eauction.dto.BidDetailDto;
import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.model.Product;
import com.cts.eauction.service.IEauctionQueryService;
import com.cts.eauction.util.EauctionUtils;



@Service
public class EauctionQueryServiceImpl implements IEauctionQueryService {

	private static final Logger LOGGER = LogManager.getLogger(EauctionQueryServiceImpl.class);

	@Autowired
	private RedisMessageProcessor redisMessageProcessor;

	/**
	 * Method to retrieve product
	 */
	@Override
	public ProductQueryDto retrieveProduct(Integer productId) {

		Product product = redisMessageProcessor.retrieveProduct(productId);
		ProductQueryDto productQueryDto = null;
		if (null != product) {
			List<BidDetailDto> dtoList = new ArrayList<>();
			product.getBidDetails().stream().forEach(bidDetail -> {
				DozerBeanMapper beanMapper = new DozerBeanMapper();
				BidDetailDto dto = beanMapper.map(bidDetail, BidDetailDto.class);
				dtoList.add(dto);
			});
			 productQueryDto = ProductQueryDto.builder().productId(product.getProductId())
					.bidEndDate(product.getBidEndDate().toString()).category(product.getCategory())
					.detailedDesc(product.getDetailedDesc()).productName(product.getProductName())
					.shortDesc(product.getShortDesc()).startingPrice(product.getStartingPrice()).bidDetails(dtoList)
					.build();
			 LOGGER.info(productQueryDto);
		}
		if (null != productQueryDto) {
			if (!CollectionUtils.isEmpty(productQueryDto.getBidDetails())) {
				List<BidDetailDto> sortedList = productQueryDto.getBidDetails().stream()
						.sorted(Comparator.comparingDouble(BidDetailDto::getBidAmount).reversed())
						.collect(Collectors.toList());
				productQueryDto.setBidDetails(sortedList);
			}
		} else {
			LOGGER.error("Invalid product id  {}", productId);
			throw new EauctionException("Error while retrieving product ",
					EauctionUtils.setErrorValues(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), Arrays.asList("Invalid product Id")));
		}
		return productQueryDto;
	}

}
