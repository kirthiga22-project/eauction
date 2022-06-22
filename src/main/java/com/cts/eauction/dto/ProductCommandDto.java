package com.cts.eauction.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductCommandDto {
	@NotNull
	@Size(min = 5, max=30)
	private String productName;
	private String shortDesc;
	private String detailedDesc;
	private Category category;
	private Double startingPrice;
	@JsonFormat( pattern ="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date bidEndDate;
	private SellerDto seller;
	private List<BidDetailDto> bidDetails;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the detailedDesc
	 */
	public String getDetailedDesc() {
		return detailedDesc;
	}

	/**
	 * @param detailedDesc the detailedDesc to set
	 */
	public void setDetailedDesc(String detailedDesc) {
		this.detailedDesc = detailedDesc;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the startingPrice
	 */
	public Double getStartingPrice() {
		return startingPrice;
	}

	/**
	 * @param startingPrice the startingPrice to set
	 */
	public void setStartingPrice(Double startingPrice) {
		this.startingPrice = startingPrice;
	}

	/**
	 * @return the bidEndDate
	 */
	public Date getBidEndDate() {
		return bidEndDate;
	}

	/**
	 * @param bidEndDate the bidEndDate to set
	 */
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	/**
	 * @return the bidDetails
	 */
	public List<BidDetailDto> getBidDetails() {
		return bidDetails;
	}

	/**
	 * @param bidDetails the bidDetails to set
	 */
	public void setBidDetails(List<BidDetailDto> bidDetails) {
		this.bidDetails = bidDetails;
	}

	/**
	 * @return the seller
	 */
	public SellerDto getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(SellerDto seller) {
		this.seller = seller;
	}
	
	

}
