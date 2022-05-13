package com.dream.filler.tracker.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace","suppressed"})
public abstract class CIAQException extends Exception {
	private static final long serialVersionUID = -6185986708336878781L;
	
	public CIAQException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public CIAQException(String msg) {
		super(msg);
	}
	
	

}
