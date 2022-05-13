package com.dream.filler.tracker.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.dream.filler.tracker.service.AppUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class TrackerAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 5*60;//5min
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 192*60*60;//8days
	
	@Value("${spring.token.second}")
    private String accessTokenSecond;

	@Value("${spring.refresh.second}")
    private String refreshTokenSecond;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserDetailsService ciaqUserDetailsService;
	
	@Autowired 
    private TokenStore tokenStore;
	
	@Autowired 
	private DataSource dataSource;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		 security
         .tokenKeyAccess("permitAll()")
         .checkTokenAccess("isAuthenticated()");
		 
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		System.out.println("accessTokenSecond: "+Integer.parseInt(accessTokenSecond));
		System.out.println("refreshTokenSecond: "+Integer.parseInt(refreshTokenSecond));
		configurer.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    endpoints
	    .pathMapping("/oauth/token", "/api/v1/tracker/oauth/token")
	    .tokenStore(this.tokenStore)	    
	    .authenticationManager(authenticationManager)
	    .userDetailsService(ciaqUserDetailsService);
	   
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	

}
