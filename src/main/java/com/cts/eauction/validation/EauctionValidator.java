package com.cts.eauction.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cts.eauction.dto.BidDetailDto;
import com.cts.eauction.dto.ProductCommandDto;
import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.exception.EauctionException;
import com.cts.eauction.util.EauctionUtils;

/**
 * Validator for eauction
 * @author cogjava296
 *
 */
@Component
public class EauctionValidator {
	private static final String PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON = "Parse exception while validating the input json";
	private static final String VALIDATION_ERROR_IN_THE_INPUT_JSON = "Validation error in the input json";
	private static final Logger LOGGER = LogManager.getLogger(EauctionValidator.class);

	/**
	 * Method to validate product
	 * 
	 * @param productCommandDto
	 */
	public void validateProduct(ProductCommandDto productCommandDto) {
		List<String> validationErrors = new ArrayList<>();
		if (productCommandDto.getBidEndDate().before(new Date())) {
			validationErrors.add("Product bid end date should be future date");
		}
		if (!CollectionUtils.isEmpty(validationErrors)) {
			com.cts.eauction.dto.Error error = EauctionUtils.setErrorValues(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors);
			LOGGER.error(VALIDATION_ERROR_IN_THE_INPUT_JSON, error);
			throw new EauctionException(VALIDATION_ERROR_IN_THE_INPUT_JSON, error);
		}
	}

	/**
	 * Method to validate product before deletion
	 * 
	 * @param productQueryDto
	 */
	public void validateProductBeforeDelete(ProductQueryDto productQueryDto) {
		List<String> validationErrors = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try {
			if (!StringUtils.isEmpty(productQueryDto.getBidEndDate())&& new Date().after(sdf.parse(productQueryDto.getBidEndDate()))) {
					validationErrors.add("Product cannot be deleted after bid end date");
			}
			if (!CollectionUtils.isEmpty(productQueryDto.getBidDetails())) {
				validationErrors.add("Product to be deleted should not have any bid entry.");
			}
		} catch (ParseException e) {
			validationErrors.add("Unparseable date - bid end date");
			com.cts.eauction.dto.Error error = EauctionUtils.setErrorValues(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors);
			LOGGER.error(PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON, error);
			throw new EauctionException(PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON, error);
		}
		if (!CollectionUtils.isEmpty(validationErrors)) {
			com.cts.eauction.dto.Error error = EauctionUtils.setErrorValues(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors);
			LOGGER.error(PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON, error);
			throw new EauctionException(VALIDATION_ERROR_IN_THE_INPUT_JSON, EauctionUtils.setErrorValues(
					HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors));
		}
	}

	public void validateProductForBidding(ProductQueryDto productQueryDto, String email, boolean updateBid) {
		List<String> validationErrors = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try {
			if (!StringUtils.isEmpty(productQueryDto.getBidEndDate())&& new Date().after(sdf.parse(productQueryDto.getBidEndDate()))) {
					validationErrors.add("Bidding cannot be placed for a product after the bid end date.");
			}
			if (!updateBid&& !CollectionUtils.isEmpty(productQueryDto.getBidDetails())) {
					List<BidDetailDto> dtoList = productQueryDto.getBidDetails().stream()
							.filter(bid -> bid.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
					if (!CollectionUtils.isEmpty(dtoList)) {
						validationErrors.add("Bidding already existing for this given email id " + email + ".");
					}

			}
		} catch (ParseException e) {
			validationErrors.add("Unparseable date - bid end date");
			com.cts.eauction.dto.Error error = EauctionUtils.setErrorValues(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors);
			LOGGER.error(PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON, error);
			throw new EauctionException(PARSE_EXCEPTION_WHILE_VALIDATING_THE_INPUT_JSON, error);
		}
		if (!CollectionUtils.isEmpty(validationErrors)) {
			com.cts.eauction.dto.Error error = EauctionUtils.setErrorValues(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(), validationErrors);
			LOGGER.error(VALIDATION_ERROR_IN_THE_INPUT_JSON, error);
			throw new EauctionException(VALIDATION_ERROR_IN_THE_INPUT_JSON, error);
		}
	}
}
