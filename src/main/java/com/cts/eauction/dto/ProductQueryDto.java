package com.cts.eauction.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1971193348394479023L;
	private Integer productId;
	private String productName;
	private String shortDesc;
	private String detailedDesc;
	private String category;
	private String startingPrice;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String bidEndDate;
	private List<BidDetailDto> bidDetails;

}
