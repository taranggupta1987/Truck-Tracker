package com.dream.filler.tracker.util;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	
	public static Optional<String> getUserForPartnerSystem() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userName = null;
		Optional<String> keyHolder = getAPIKeyForPartnerSystem();
		if(keyHolder.isPresent()) {
			if(Objects.isNull(request.getHeader("user"))) {
				throw new RuntimeException("Requested USER is missing");
			}else {
				userName = request.getHeader("user");
			}
		}		
		return Optional.ofNullable(userName);
		
	}
	
	public static Optional<String> getAPIAccessLevelForPartnerSystem() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String accessLevel = null;
		Optional<String> keyHolder = getAPIKeyForPartnerSystem();
		if(keyHolder.isPresent()) {
			if(Objects.isNull(request.getHeader("access-level"))) {
				throw new RuntimeException("Requested USER is missing");
			}else {
				accessLevel = request.getHeader("access-level");
			}
		}		
		return Optional.ofNullable(accessLevel);
		
	}
	
	public static Optional<String> getAPIKeyForPartnerSystem() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String apiKey = null;
		if(Objects.nonNull(request.getHeader("x-api-key"))){
			apiKey = request.getHeader("x-api-key");
		}		
		return Optional.ofNullable(apiKey);
		
	}

}
