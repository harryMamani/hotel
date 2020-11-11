package com.pe.infosis.hotel.service;

import java.util.List;

import com.pe.infosis.hotel.bean.requets.ActualizarHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CambiaEstadoHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CrearHabitacionRequets;
import com.pe.infosis.hotel.bean.response.HabitacionResponse;
import com.pe.infosis.hotel.bean.response.HabitacionesLibresResponse;
import com.pe.infosis.hotel.exception.HotelException;

public interface HabitacionService {

	public List<HabitacionResponse> listar();
	
	public void crear(CrearHabitacionRequets requets) throws HotelException;
	
	public void actualizar(String puerta, ActualizarHabitacionRequets requets) throws HotelException;
	
	public void eliminar(String puerta) throws HotelException;
	
	public List<HabitacionResponse> listarXEstado(String estado);
	
	public List<HabitacionResponse> listarXTipo(String tipo);
	
	void cambiarEstado(CambiaEstadoHabitacionRequets requets) throws HotelException;
	
	public HabitacionesLibresResponse habitacionesLibres();
	
}
