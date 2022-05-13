package com.dream.filler.tracker.exception;

public class ActivityHistoryException extends CIAQException {
	private static final long serialVersionUID = 4569882908189926019L;

	public ActivityHistoryException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public ActivityHistoryException(String msg) {
		super(msg);
	}
}
