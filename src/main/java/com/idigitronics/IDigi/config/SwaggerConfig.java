/**
 * 
 */
package com.idigitronics.IDigi.config;

/**
 * @author ADITHI
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	    public SwaggerConfig() {

//	        String swaggerEnabled = System.getProperty("swagger.enabled", "false");
	        String swaggerEnabled = System.getProperty("swagger.enabled", "true");

	        if (!"true".equalsIgnoreCase(swaggerEnabled)) {
	            throw new RuntimeException("Swagger Disabled");
	        }
	    }

	    @Bean
	    public Docket api() {

	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage(
	                        "com.idigitronics.IDigi.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }
}
