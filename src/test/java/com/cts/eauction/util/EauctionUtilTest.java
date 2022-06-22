package com.cts.eauction.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EauctionUtilTest {

	@Test
	public void testEauctionUtils() {
		assertNotNull(EauctionUtils.getInstance());
	}

	@Test
	public void testExceptionHandler() {
		assertNotNull(EauctionUtils.setErrorValues(500, "test", null));
	}
}
