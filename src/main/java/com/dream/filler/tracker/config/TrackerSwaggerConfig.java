package com.dream.filler.tracker.config;

import java.util.Collections;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class TrackerSwaggerConfig {
	
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	
	private final ServletContext servletContext;
	private static final String BASEPATH = "/api/v1/ciaq";
	
	public TrackerSwaggerConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.pathProvider(new RelativePathProvider(servletContext) {
        			@Override
        			protected String applicationPath() {
        				return BASEPATH;
        			}
        			
        			@Override
        			public String getOperationPath(String operationPath) {
        				String result = super.getOperationPath(operationPath);
        				if(result.startsWith(BASEPATH)) {
        					result = result.substring(BASEPATH.length());
        				}
        				return result;
        			}
        		})
        		.enable(profile.equalsIgnoreCase("dev")||profile.equalsIgnoreCase("ws")?true:false)
        		//.ignoredParameterTypes(UsernamePasswordAuthenticationToken.class)
				.ignoredParameterTypes(OAuth2Authentication.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.broan.ciaq.controller"))
                //.paths(regex("/api/v1/ciaq.*"))
                .build().apiInfo(metaData());
    }
	
	private ApiInfo metaData() {
		return new ApiInfo(
			      "CIAQ REST APIs", 
			      "Some custom description of API.", 
			      "API TOS", 
			      "Terms of service", 
			      new Contact("Gaurav Sahay", "www.test.com", "gaurav@test.com"), 
			      "License of API", "API license URL", Collections.emptyList());
    }

}
