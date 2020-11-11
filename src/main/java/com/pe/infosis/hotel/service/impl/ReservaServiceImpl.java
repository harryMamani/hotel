package com.pe.infosis.hotel.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.infosis.hotel.bean.requets.ReservaRequets;
import com.pe.infosis.hotel.bean.response.ReservaResponse;
import com.pe.infosis.hotel.comun.Constantes;
import com.pe.infosis.hotel.entity.Habitacion;
import com.pe.infosis.hotel.entity.Reserva;
import com.pe.infosis.hotel.entity.Usuario;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.repository.HabitacionRepository;
import com.pe.infosis.hotel.repository.ReservaRepository;
import com.pe.infosis.hotel.service.ReservaService;

@Service
@Transactional
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	HabitacionRepository habitacionDao;
	
	@Autowired
	ReservaRepository reservaDao;
	
	@Override
	public ReservaResponse crearReserva(ReservaRequets requets) throws HotelException {

		List<Habitacion> habitaciones = habitacionDao.findByTipoAndEstado(requets.getTipoHabitacion(), Constantes.ESTADO_HABITACION_LIBRE);
		if(habitaciones.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.HABITACIONES_NO_DISPONIBLES);
		}
		Habitacion habitacion = habitaciones.get(0);
		Integer personas = requets.getCantidadPersonas();
		if(personas > habitacion.getTipo().getCapacidad()) {
			throw new HotelException(requets, ErrorCodeEnum.CAPACIDAD_EXCEDIDA);
		}
		habitacionDao.cambiarEstado(habitacion.getPuerta(), Constantes.ESTADO_HABITACION_OCUPADO);
		Reserva reserva = new Reserva();
		reserva.setUsuario(new Usuario(requets.getNombres(), requets.getApellidos(), Constantes.USUARIO_CLIENTE));
		reserva.setHabitacion(habitacion);
		reserva.setCantidadPersonas(requets.getCantidadPersonas());
		reserva.setFecha(new Date());
		reservaDao.save(reserva);
		reserva.setCodigo(String.format("RE%s%06d", habitacion.getPuerta(), reserva.getId()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		
		ReservaResponse response = new ReservaResponse();
		response.setCodigo(reserva.getCodigo());
		response.setNombres(reserva.getUsuario().getNombres());
		response.setApellidos(reserva.getUsuario().getApellidos());
		response.setPuerta(habitacion.getPuerta());
		response.setTipoHabitacion(habitacion.getTipo().getDescripcion());
		response.setCantidadPersonas(reserva.getCantidadPersonas());
		response.setFechaReserva(sdf.format(reserva.getFecha()));
		
		return response;
	}

}
