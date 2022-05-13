package com.dream.filler.tracker.exception;

public class TruckException extends CIAQException {
	private static final long serialVersionUID = -6924880008079611841L;

	public TruckException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public TruckException(String msg) {
		super(msg);
	}

}
