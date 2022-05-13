package com.dream.filler.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("WebMvcConfigurer addCorsMappings");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
					.exposedHeaders("Content-Type, Authorization, Access-Control-Allow-Origin, Content-Length")
					.allowCredentials(false).maxAge(3600);
			}
		};
	}
}
