package com.pe.infosis.hotel.service;

import com.pe.infosis.hotel.bean.requets.ReservaRequets;
import com.pe.infosis.hotel.bean.response.ReservaResponse;
import com.pe.infosis.hotel.exception.HotelException;

public interface ReservaService {

	public ReservaResponse crearReserva(ReservaRequets requets) throws HotelException;
}
