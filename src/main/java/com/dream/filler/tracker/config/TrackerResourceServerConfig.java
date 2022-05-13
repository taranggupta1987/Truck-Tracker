package com.dream.filler.tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

//@Order(6)
@Configuration
@EnableResourceServer
public class TrackerResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("resource_id").stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
	     .antMatchers("/h2/**", 
					"/oauth/token", "/oauth/authorize", "/api/v1/tracker/oauth/token", "/api/v1/tracker/register"
					).permitAll()
	     .antMatchers("/api/**").authenticated().and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
	}
}
