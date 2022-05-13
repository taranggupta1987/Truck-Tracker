package com.dream.filler.tracker.exception;

public class UserException extends CIAQException {
	private static final long serialVersionUID = -6924880008079611841L;

	public UserException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public UserException(String msg) {
		super(msg);
	}

}
