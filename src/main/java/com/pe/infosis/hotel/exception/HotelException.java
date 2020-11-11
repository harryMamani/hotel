package com.pe.infosis.hotel.exception;

import com.pe.infosis.hotel.bean.requets.CrearHabitacionRequets;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;

public class HotelException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Object requets;
	private final ErrorCodeEnum errorCode;
	
	public HotelException(Object requets, ErrorCodeEnum errorCode) {
		super(errorCode.getMessage());
		this.errorCode=errorCode;
		this.requets = requets;
	}
	
	public HotelException(CrearHabitacionRequets requets, ErrorCodeEnum errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode=errorCode;
		this.requets = requets;
	}

	public Object getRequets() {
		return requets;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

}
