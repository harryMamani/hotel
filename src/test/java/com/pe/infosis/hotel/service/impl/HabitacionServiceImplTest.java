package com.pe.infosis.hotel.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.pe.infosis.hotel.Application;
import com.pe.infosis.hotel.bean.requets.ActualizarHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CambiaEstadoHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CrearHabitacionRequets;
import com.pe.infosis.hotel.bean.response.HabitacionResponse;
import com.pe.infosis.hotel.bean.response.HabitacionesLibresResponse;
import com.pe.infosis.hotel.entity.EstadoHabitacion;
import com.pe.infosis.hotel.entity.Habitacion;
import com.pe.infosis.hotel.entity.TipoHabitacion;
import com.pe.infosis.hotel.entity.TransicionEstado;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.repository.EstadoHabitacionRepository;
import com.pe.infosis.hotel.repository.HabitacionRepository;
import com.pe.infosis.hotel.repository.TipoHabitacionRepository;
import com.pe.infosis.hotel.repository.TransicionEstadoRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Application.class)
class HabitacionServiceImplTest {

	@InjectMocks
	HabitacionServiceImpl habitacionService;

	@Mock
	HabitacionRepository habitacionDao;
	
	@Mock
	TipoHabitacionRepository tipoHabitacionDao;

	@Mock
	EstadoHabitacionRepository estadoHabitacionDao;
	
	@Mock
	TransicionEstadoRepository transicionEstadoDao;
	
	Optional<TipoHabitacion> tipoHabitacion;
	
	Optional<EstadoHabitacion> estadoHabitacion;
	
	Optional<Habitacion> habitacion;
	
	Iterable<Habitacion> habitaciones;
	
	List<Habitacion> lista;
	
	TransicionEstado transicionEstado;
	
	Iterable<TipoHabitacion> tipoHabitaciones;
	
	@BeforeEach
	public void setUp() throws Exception {
		habitacionService = new HabitacionServiceImpl();
		MockitoAnnotations.initMocks(this);
		
		TipoHabitacion tipo = new TipoHabitacion();
		tipo.setCapacidad(4);
		tipo.setCodigo("NOR");
		tipo.setDescripcion("Normal");
		
		TipoHabitacion tipoEstandar = new TipoHabitacion();
		tipo.setCapacidad(3);
		tipo.setCodigo("EST");
		tipo.setDescripcion("Estandar");
		
		EstadoHabitacion estado = new EstadoHabitacion();
		estado.setCodigo("LIB");
		estado.setDescripcion("Libre");

		Habitacion h1 = new Habitacion();
		h1.setEstado(estado);
		h1.setTipo(tipo);
		h1.setPuerta("202");
		h1.setPiso("2");
		
		Habitacion h2 = new Habitacion();
		h2.setEstado(estado);
		h2.setTipo(tipo);
		h2.setPuerta("302");
		h2.setPiso("3");
		
		habitacion = Optional.of(h1);
		
		lista= new ArrayList<Habitacion>();
		lista.add(h1);
		lista.add(h2);
		
		habitaciones = lista;
		
		tipoHabitacion = Optional.of(tipo);
		estadoHabitacion = Optional.of(estado);
		
		transicionEstado = new TransicionEstado();
		transicionEstado.setId(1);
		transicionEstado.setOrigen(estado);
		transicionEstado.setFin(estado);
		
		List<TipoHabitacion> listaTipo = new ArrayList<TipoHabitacion>();
		listaTipo.add(tipo);
		listaTipo.add(tipoEstandar);
		tipoHabitaciones = listaTipo;
	}
	
	@Test
	void testListar() {
		when(habitacionDao.findAll()).thenReturn(habitaciones);
		List<HabitacionResponse> lista = habitacionService.listar(); 
		assertTrue(!lista.isEmpty());
	}

	@Test
	void testCrear() {
		boolean correcto=true;
		CrearHabitacionRequets requets = new CrearHabitacionRequets();
		requets.setPuerta("501");
		requets.setPiso("5");
		requets.setTipo("NOR");
		requets.setEstado("LIB");
		
		try {
			when(habitacionDao.existsById(anyString())).thenReturn(false);
			when(tipoHabitacionDao.findById(anyString())).thenReturn(tipoHabitacion);
			when(estadoHabitacionDao.findById(anyString())).thenReturn(estadoHabitacion);
			when(habitacionDao.save(any())).thenReturn(new Habitacion());
			habitacionService.crear(requets);
		} catch (HotelException e) {
			correcto = false;
		}
		assertTrue(correcto);
	}

	@Test
	void testActualizar() {
		boolean correcto=true;
		ActualizarHabitacionRequets requets = new ActualizarHabitacionRequets();
		requets.setPiso("5");
		requets.setTipo("NOR");
		requets.setEstado("LIB");
		
		try {
			when(habitacionDao.existsById(anyString())).thenReturn(true);
			when(tipoHabitacionDao.findById(anyString())).thenReturn(tipoHabitacion);
			when(estadoHabitacionDao.findById(anyString())).thenReturn(estadoHabitacion);
			when(habitacionDao.save(any())).thenReturn(new Habitacion());
			habitacionService.actualizar("201", requets);
		} catch (HotelException e) {
			correcto = false;
		}
		assertTrue(correcto);
	}

	@Test
	void testEliminar() {
		boolean correcto=true;
		
		try {
			when(habitacionDao.findById(anyString())).thenReturn(habitacion);
			habitacionService.eliminar("201");
			Mockito.verify(habitacionDao, times(1)).delete(any());
		} catch (HotelException e) {
			correcto = false;
		}
		assertTrue(correcto);
	}

	@Test
	void testListarXEstado() {
		when(habitacionDao.findByEstado(any())).thenReturn(lista);
		List<HabitacionResponse> listaResponse = habitacionService.listarXEstado("LIB"); 
		assertTrue(!listaResponse.isEmpty());
	}

	@Test
	void testListarXTipo() {
		when(habitacionDao.findByTipo(any())).thenReturn(lista);
		List<HabitacionResponse> listaResponse = habitacionService.listarXTipo("NOR"); 
		assertTrue(!listaResponse.isEmpty());
	}

	@Test
	void testCambiarEstado() {
		boolean correcto=true;
		CambiaEstadoHabitacionRequets requets = new CambiaEstadoHabitacionRequets();
		requets.setPuerta("501");
		requets.setEstado("LIB");
		
		try {
			when(habitacionDao.findById(anyString())).thenReturn(habitacion);
			when(estadoHabitacionDao.findById(anyString())).thenReturn(estadoHabitacion);
			when(transicionEstadoDao.findByOrigenAndFin(anyString(), anyString())).thenReturn(transicionEstado);
			habitacionService.cambiarEstado(requets);
			Mockito.verify(habitacionDao, times(1)).cambiarEstado(anyString(), anyString());
		} catch (HotelException e) {
			correcto = false;
		}
		assertTrue(correcto);
	}
	
	@Test
	void testHabitacionesLibres() {
		when(tipoHabitacionDao.findAll()).thenReturn(tipoHabitaciones);
		when(habitacionDao.countByTipoandEstado(anyString(), anyString())).thenReturn(3);
		HabitacionesLibresResponse response = habitacionService.habitacionesLibres(); 
		assertTrue(!response.getHabitaciones().isEmpty());
	}

	
	
}
