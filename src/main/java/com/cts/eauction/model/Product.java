package com.cts.eauction.model;



import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	@Column(name = "productName")
	private String productName;
	@Column(name = "shortDesc")
	private String shortDesc;
	@Column(name = "detailDesc")
	private String detailedDesc;
	@Column(name = "firstName")
	private String category;
	@Column(name = "startingPrice")
	private String startingPrice;
	@Column(name = "bidEndDate")
	private Timestamp bidEndDate;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private Seller seller;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
	private List<BidDetail> bidDetails;

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

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
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the startingPrice
	 */
	public String getStartingPrice() {
		return startingPrice;
	}

	/**
	 * @param startingPrice the startingPrice to set
	 */
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	/**
	 * @return the bidEndDate
	 */
	public Timestamp getBidEndDate() {
		return bidEndDate;
	}

	/**
	 * @param bidEndDate the bidEndDate to set
	 */
	public void setBidEndDate(Timestamp bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	/**
	 * @return the seller
	 */
	@JsonManagedReference
	public Seller getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * @return the bidDetails
	 */
	@JsonManagedReference
	public List<BidDetail> getBidDetails() {
		return bidDetails;
	}

	/**
	 * @param bidDetails the bidDetails to set
	 */
	public void setBidDetails(List<BidDetail> bidDetails) {
		this.bidDetails = bidDetails;
	}

}
