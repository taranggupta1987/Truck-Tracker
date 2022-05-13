package com.dream.filler.tracker.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SimpleCORSFilter extends OncePerRequestFilter{//implements Filter {

    private final Logger log = LoggerFactory.getLogger(SimpleCORSFilter.class);

    public SimpleCORSFilter() {
        log.info("SimpleCORSFilter init");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
    	log.info("doFilterInternal "+request.getMethod()+", URL : "+request.getRequestURL());

    	if(request.getRequestURI().endsWith("/oauth/token")) {
    		Enumeration<String> params = request.getParameterNames(); 
    		while(params.hasMoreElements()){
    			String paramName = params.nextElement();
    			if(paramName.equalsIgnoreCase("username")) {
    				log.info("Login email id  : "+request.getParameter(paramName));
    			}
    			if(paramName.equalsIgnoreCase("grant_type")) {
    				log.info("Getting token using : "+request.getParameter(paramName));
    			}
    			log.info(paramName + " : "+request.getParameter(paramName));
    		}
    	}else if(request.getRequestURI().endsWith("/user/login")) {
    		Enumeration<String> headerNames = request.getHeaderNames();
    		while(headerNames.hasMoreElements()) {
    			String headerName = headerNames.nextElement();
    			if(headerName.equalsIgnoreCase("logintype")) {
    				log.info("Request for  - " + request.getHeader(headerName));	  
    			}
    		}
    	}
        
    	response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token, x-api-key, user,access-level");
        response.addHeader("Access-Control-Expose-Headers", "authorization, content-type, xsrf-token, Access-Control-Allow-Origin");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else { 
            filterChain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
    }
}