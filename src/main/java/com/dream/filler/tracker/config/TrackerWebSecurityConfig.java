package com.dream.filler.tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dream.filler.tracker.service.AppUserDetailsService;
import com.dream.filler.tracker.util.Constants;

//@Order(2)

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class TrackerWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppUserDetailsService ciaqUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ciaqUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	private String [] publicUrls = {
			"/h2/**",
			"/oauth/token", "/oauth/authorize", "/api/v1/tracker/oauth/token", "/api/v1/tracker/register"
	};
	
	private String [] userGuestAdminUrls = {
	};
	
	private String [] portalUserUrls = {
	};
	
	private String [] guestAdminUrls= {
	};
	
	private String [] adminUrls= {
			"/swagger-ui.html",
			"/api/v1/tracker/admin/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requiresChannel().anyRequest().requiresSecure();;
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http		
		.authorizeRequests()
		.antMatchers(publicUrls).permitAll()
		.antMatchers(userGuestAdminUrls).hasAnyRole(Constants.ROLE_USER,Constants.ROLE_GUEST,Constants.ROLE_ADMIN)
		.antMatchers(portalUserUrls).hasRole(Constants.ROLE_PORTAL_USER)
		.antMatchers(guestAdminUrls).hasAnyRole(Constants.ROLE_GUEST,Constants.ROLE_ADMIN)
		.antMatchers(adminUrls).hasRole(Constants.ROLE_ADMIN)
		.anyRequest().authenticated().and()
		.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	 @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//  @Bean public RestTemplate restTemplate(RestTemplateBuilder
//  restTemplateBuilder) { 
//	  return restTemplateBuilder.requestFactory(() -> validateSSL()).build(); 
//	  
//  }
//	 
//	
//	private HttpComponentsClientHttpRequestFactory validateSSL(){
//        String location = "broan-nutone-digicert2021.keystore";
//        String pass = "broaniot";
//        SSLContext sslContext = null;
//        try{
//            sslContext = SSLContextBuilder
//                    .create()
//                    .loadTrustMaterial(ResourceUtils.getFile(location), pass.toCharArray())
//                    .build();
//        }catch (Exception e){
//
//        }
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,new LocalHostnameVerifier());
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return requestFactory;
//    }

//    private class LocalHostnameVerifier implements HostnameVerifier {
//        @Override
//        public boolean verify(String s, SSLSession sslSession) {
//            return "localhost".equalsIgnoreCase(s) || "127.0.0.1".equals(s) || "iotdev.broan-nutone.com".equals(s);
//        }
//    } 


}
