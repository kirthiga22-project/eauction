package com.cts.eauction.config;

import org.junit.Test;

import junit.framework.TestCase;

public class SwaggerConfigTest extends TestCase {

	@Test
	public void testSwaggerConfig() {
		SwaggerConfig config = new SwaggerConfig();
		assertNotNull(config.sortSchemasAlphabetically());
		assertNotNull(config.euactionOpenApiConfig());
	}

}
