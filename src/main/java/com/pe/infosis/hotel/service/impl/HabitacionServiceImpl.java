package com.pe.infosis.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.infosis.hotel.bean.HabitacionDisponible;
import com.pe.infosis.hotel.bean.requets.ActualizarHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CambiaEstadoHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CrearHabitacionRequets;
import com.pe.infosis.hotel.bean.response.HabitacionResponse;
import com.pe.infosis.hotel.bean.response.HabitacionesLibresResponse;
import com.pe.infosis.hotel.comun.Constantes;
import com.pe.infosis.hotel.entity.EstadoHabitacion;
import com.pe.infosis.hotel.entity.Habitacion;
import com.pe.infosis.hotel.entity.TipoHabitacion;
import com.pe.infosis.hotel.entity.TransicionEstado;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.repository.EstadoHabitacionRepository;
import com.pe.infosis.hotel.repository.HabitacionRepository;
import com.pe.infosis.hotel.repository.TipoHabitacionRepository;
import com.pe.infosis.hotel.repository.TransicionEstadoRepository;
import com.pe.infosis.hotel.service.HabitacionService;

@Service
@Transactional
public class HabitacionServiceImpl implements HabitacionService {

	@Autowired
	HabitacionRepository habitacionDao;

	@Autowired
	TipoHabitacionRepository tipoHabitacionDao;

	@Autowired
	EstadoHabitacionRepository estadoHabitacionDao;
	
	@Autowired
	TransicionEstadoRepository transicionEstadoDao;

	@Override
	public List<HabitacionResponse> listar() {

		List<HabitacionResponse> lista = new ArrayList<HabitacionResponse>();
		Iterable<Habitacion> habitaciones = habitacionDao.findAll();
		habitaciones.forEach(h -> {
			HabitacionResponse response = new HabitacionResponse();
			response.setPuerta(h.getPuerta());
			response.setPiso(h.getPiso());
			response.setTipo(h.getTipo().getCodigo());
			response.setEstado(h.getEstado().getCodigo());
			lista.add(response);
		});

		return lista;
	}

	@Override
	public void crear(CrearHabitacionRequets requets) throws HotelException {

		String puerta = requets.getPuerta();
		if(habitacionDao.existsById(puerta)) {
			throw new HotelException(requets, ErrorCodeEnum.HABITACION_YA_EXISTE);
		}
		
		String tipo = requets.getTipo();
		Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionDao.findById(tipo);
		if (tipoHabitacion.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.TIPO_HABITACION_NO_EXISTE);
		}

		String estado = requets.getEstado();
		Optional<EstadoHabitacion> estadoHabitacion;
		if (estado == null) {
			estadoHabitacion = Optional.of(new EstadoHabitacion(Constantes.ESTADO_HABITACION_LIBRE));
		} else {
			estadoHabitacion = estadoHabitacionDao.findById(estado);
			if (estadoHabitacion.isEmpty()) {
				throw new HotelException(requets, ErrorCodeEnum.ESTADO_HABITACION_NO_EXISTE);
			}

		}

		Habitacion habitacion = new Habitacion();
		habitacion.setPuerta(puerta);
		habitacion.setPiso(requets.getPiso());
		habitacion.setTipo(tipoHabitacion.get());
		habitacion.setEstado(estadoHabitacion.get());

		habitacionDao.save(habitacion);

	}

	@Override
	public void actualizar(String puerta, ActualizarHabitacionRequets requets) throws HotelException {
		if(!habitacionDao.existsById(puerta)) {
			throw new HotelException(requets, ErrorCodeEnum.HABITACION_NO_EXISTE);
		}
		String tipo = requets.getTipo();
		Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionDao.findById(tipo);
		if (tipoHabitacion.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.TIPO_HABITACION_NO_EXISTE);
		}

		String estado = requets.getEstado();
		Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionDao.findById(estado);
		if (estadoHabitacion.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.ESTADO_HABITACION_NO_EXISTE);
		}

		Habitacion habitacion = new Habitacion();
		habitacion.setPuerta(puerta);
		habitacion.setPiso(requets.getPiso());
		habitacion.setTipo(tipoHabitacion.get());
		habitacion.setEstado(estadoHabitacion.get());

		habitacionDao.save(habitacion);
	}

	@Override
	public void eliminar(String puerta) throws HotelException {
		Optional<Habitacion> habitacion = habitacionDao.findById(puerta);
		if(habitacion.isEmpty()) {
			throw new HotelException("puerta: "+puerta, ErrorCodeEnum.HABITACION_NO_EXISTE);
		}
		habitacionDao.delete(habitacion.get());
	}

	@Override
	public List<HabitacionResponse> listarXEstado(String estado) {
		List<HabitacionResponse> lista = new ArrayList<HabitacionResponse>();
		Iterable<Habitacion> habitaciones = habitacionDao.findByEstado(estado);
		habitaciones.forEach(h -> {
			HabitacionResponse response = new HabitacionResponse();
			response.setPuerta(h.getPuerta());
			response.setPiso(h.getPiso());
			response.setTipo(h.getTipo().getCodigo());
			response.setEstado(h.getEstado().getCodigo());
			lista.add(response);
		});

		return lista;
	}

	@Override
	public List<HabitacionResponse> listarXTipo(String tipo) {
		List<HabitacionResponse> lista = new ArrayList<HabitacionResponse>();
		Iterable<Habitacion> habitaciones = habitacionDao.findByTipo(tipo);
		habitaciones.forEach(h -> {
			HabitacionResponse response = new HabitacionResponse();
			response.setPuerta(h.getPuerta());
			response.setPiso(h.getPiso());
			response.setTipo(h.getTipo().getCodigo());
			response.setEstado(h.getEstado().getCodigo());
			lista.add(response);
		});

		return lista;
	}

	@Override
	public void cambiarEstado(CambiaEstadoHabitacionRequets requets) throws HotelException {
		String puerta = requets.getPuerta();
		Optional<Habitacion> habitacion = habitacionDao.findById(puerta);
		if (habitacion.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.HABITACION_NO_EXISTE);
		}

		String estado = requets.getEstado();
		Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionDao.findById(estado);
		if (estadoHabitacion.isEmpty()) {
			throw new HotelException(requets, ErrorCodeEnum.ESTADO_HABITACION_NO_EXISTE);
		}

		TransicionEstado transicionEstado = transicionEstadoDao.findByOrigenAndFin(habitacion.get().getEstado().getCodigo(), estado);
		if(transicionEstado == null) {
			throw new HotelException(requets, ErrorCodeEnum.TRANSICION_NO_PERMITIDA);
		}
		
		habitacionDao.cambiarEstado(puerta, estado);
	}

	@Override
	public HabitacionesLibresResponse habitacionesLibres() {
	
		Iterable<TipoHabitacion> tipoHabitaciones = tipoHabitacionDao.findAll();
		
		HabitacionesLibresResponse response = new HabitacionesLibresResponse();
		tipoHabitaciones.forEach(t -> {
			int cantidad = habitacionDao.countByTipoandEstado(t.getCodigo(), Constantes.ESTADO_HABITACION_LIBRE);
			if(cantidad > 0) {
				HabitacionDisponible disponible = new HabitacionDisponible(t.getDescripcion(), t.getCapacidad(), cantidad);
				response.getHabitaciones().add(disponible);
			}
		});
		
		return response;
	}

}
