package com.cts.eauction.model;

import java.io.Serializable;

public class JwtResponse  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3112548325713130622L;
	private final String jwtToken;
	
	public JwtResponse(String jwtToken) {
		this.jwtToken=jwtToken;
	}

	/**
	 * @return the jwtToken
	 */
	public String getJwtToken() {
		return jwtToken;
	}

	
}
