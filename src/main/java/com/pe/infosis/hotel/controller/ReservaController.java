package com.pe.infosis.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.infosis.hotel.bean.Response;
import com.pe.infosis.hotel.bean.requets.ReservaRequets;
import com.pe.infosis.hotel.bean.response.ReservaResponse;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.service.ReservaService;

@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController {

	@Autowired
	ReservaService reservaService;
	
	@PostMapping("/")
	public ResponseEntity<Response<ReservaResponse>> crearReserva(@Valid @RequestBody ReservaRequets requets) throws HotelException{
		ReservaResponse reserva = reservaService.crearReserva(requets);
		Response<ReservaResponse> response = new Response<ReservaResponse>();
		response.setData(reserva);
		response.setStatus(ErrorCodeEnum.OK.status());
		return ResponseEntity.ok(response);
	}
}
