package com.cts.eauction.validation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.eauction.dto.BidDetailDto;
import com.cts.eauction.dto.ProductCommandDto;
import com.cts.eauction.dto.ProductQueryDto;
import com.cts.eauction.exception.EauctionException;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EauctionValidator.class)
public class EauctionValidationTest extends TestCase {

	@Autowired
	private EauctionValidator validator;
	
	@Test(expected = EauctionException.class)
	public void testValidateProduct() {
		ProductCommandDto commandDto=new ProductCommandDto();
		Calendar cal=Calendar.getInstance();
		cal.set(2022, 4, 2);
		commandDto.setBidEndDate(cal.getTime());
		validator.validateProduct(commandDto);
	}
	
		/**
	 * Method to validate product before deletion
	 * 
	 * @param productQueryDto
	 */
	@Test(expected = EauctionException.class)
	public void testValidateProductBeforeDelete() {
		ProductQueryDto productQueryDto = new ProductQueryDto();
		Calendar cal=Calendar.getInstance();
		cal.set(2022, 4, 3);
		Date date = cal.getTime();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		productQueryDto.setBidEndDate(sdf.format(date));
		List<BidDetailDto> list=new ArrayList<>();
		BidDetailDto dto=new BidDetailDto();
		dto.setEmail("email");
		list.add(dto);
		productQueryDto.setBidDetails(list);
		validator.validateProductBeforeDelete(productQueryDto);
	}

	@Test(expected = EauctionException.class)
	public void testValidateProductBeforeDeleteException() {
		ProductQueryDto productQueryDto = new ProductQueryDto();
		Calendar cal=Calendar.getInstance();
		cal.set(2022, 4, 3);
		productQueryDto.setBidEndDate(cal.getTime().toString());
		List<BidDetailDto> list=new ArrayList<>();
		BidDetailDto dto=new BidDetailDto();
		dto.setEmail("email");
		list.add(dto);
		productQueryDto.setBidDetails(list);
		validator.validateProductBeforeDelete(productQueryDto);
	}
	@Test(expected = EauctionException.class)
	public void testValidateProductForBidding() {
		ProductQueryDto productQueryDto = new ProductQueryDto();
		Calendar cal=Calendar.getInstance();
		cal.set(2022, 3, 6);
		Date date = cal.getTime();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		productQueryDto.setBidEndDate(sdf.format(date));
		List<BidDetailDto> list=new ArrayList<>();
		BidDetailDto dto=new BidDetailDto();
		dto.setEmail("email");
		list.add(dto);
		productQueryDto.setBidDetails(list);
		validator.validateProductForBidding(productQueryDto, "email", false);
		}
	
	@Test(expected = EauctionException.class)
	public void testValidateProductForBiddingException() {
		ProductQueryDto productQueryDto = new ProductQueryDto();
		Calendar cal=Calendar.getInstance();
		cal.set(2022, 5, 6);
		productQueryDto.setBidEndDate(cal.getTime().toString());
		List<BidDetailDto> list=new ArrayList<>();
		BidDetailDto dto=new BidDetailDto();
		dto.setEmail("email");
		list.add(dto);
		productQueryDto.setBidDetails(list);
		validator.validateProductForBidding(productQueryDto, "email", true);
		}

}
