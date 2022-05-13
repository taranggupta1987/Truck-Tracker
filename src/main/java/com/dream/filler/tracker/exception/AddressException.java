package com.dream.filler.tracker.exception;

public class AddressException extends CIAQException {
	private static final long serialVersionUID = -2762078330021168598L;

	public AddressException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public AddressException(String msg) {
		super(msg);
	}

}
