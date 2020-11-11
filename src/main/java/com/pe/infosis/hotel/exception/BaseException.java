package com.pe.infosis.hotel.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
    public BaseException(String message) {
        super(message);
        this.message = message;
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
	public String getMessage() {
		return message;
	}

}
