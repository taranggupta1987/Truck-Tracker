package com.dream.filler.tracker.exception;

public class ResourceException extends CIAQException {
	private static final long serialVersionUID = 253462413366165750L;

	public ResourceException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public ResourceException(String msg) {
		super(msg);
	}

}
