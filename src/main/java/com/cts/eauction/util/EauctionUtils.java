package com.cts.eauction.util;

import java.util.List;

import com.cts.eauction.dto.Error;

public class EauctionUtils {
	
	private EauctionUtils() {
		//Default constructor
	}
	
	private static EauctionUtils utils = null;
	
	public static EauctionUtils getInstance() {
		if(utils ==null) {
			utils= new EauctionUtils();
		}
		return utils;
	}
	
	/**
	 * Method to set error values
	 * @param errorCode
	 * @param errorMessage
	 * @param errorDetails
	 * @return
	 */
	public static Error setErrorValues(int errorCode, String errorMessage, List<String> errorDetails) {
		Error error = new Error();
		error.setErrorCode(errorCode);
		error.setErrorMessage(errorMessage);
		error.setErrorDetails(errorDetails);
		return error;
		
	}

}
