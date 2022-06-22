package com.cts.eauction.dto;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class ErrorTest extends TestCase {

	@Test
	public void testError() {
		Error error = new Error();
		error.setErrorCode(500);
		error.setErrorDetails(new ArrayList<>());
		error.setPath("//");
		assertEquals(500, error.getErrorCode());
		assertEquals("//", error.getPath());
		assertNotNull(error.getErrorDetails());
	}
}
