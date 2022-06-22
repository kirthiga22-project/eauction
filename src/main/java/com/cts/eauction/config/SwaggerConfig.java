package com.cts.eauction.config;


import java.util.Map;
import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI euactionOpenApiConfig() {
		return new OpenAPI().addServersItem(new Server().url("/")).info(new Info().title("Eauction APIs")
				.description("Eauction Api reference for developers"));
	}
	
	@Bean
	public OpenApiCustomiser sortSchemasAlphabetically() {
		return openApi-> {
			@SuppressWarnings("rawtypes")
			Map<String, Schema> schemas = openApi.getComponents().getSchemas();
			openApi.getComponents().setSchemas(new TreeMap<>(schemas));
		};
	}


}
