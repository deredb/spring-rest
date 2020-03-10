package com.dere.rest.restspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.dere.rest.restspring.converters.StringtoDateConverter;

@Configuration
@ComponentScan(basePackages="Com.dere.rest.restspring")
public class ApplicationConfig extends WebMvcConfigurationSupport{

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringtoDateConverter());
	}
}
