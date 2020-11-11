package com.pe.infosis.hotel.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.pe.infosis.hotel.bean.requets.ReservaRequets;
import com.pe.infosis.hotel.bean.response.ReservaResponse;
import com.pe.infosis.hotel.entity.EstadoHabitacion;
import com.pe.infosis.hotel.entity.Habitacion;
import com.pe.infosis.hotel.entity.Reserva;
import com.pe.infosis.hotel.entity.TipoHabitacion;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.repository.HabitacionRepository;
import com.pe.infosis.hotel.repository.ReservaRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Application.class)
class ReservaServiceImplTest {
	
	@InjectMocks
	ReservaServiceImpl reservaService;
	
	@Mock
	HabitacionRepository habitacionDao;
	
	@Mock
	ReservaRepository reservaDao;
	
	List<Habitacion> lista;
	
	@BeforeEach
	public void setUp() throws Exception {
		reservaService = new ReservaServiceImpl();
		MockitoAnnotations.initMocks(this);
		
		TipoHabitacion tipo = new TipoHabitacion();
		tipo.setCapacidad(4);
		tipo.setCodigo("NOR");
		tipo.setDescripcion("Normal");
		
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
		
		lista= new ArrayList<Habitacion>();
		lista.add(h1);
		lista.add(h2);
	}

	@Test
	void testCrearReserva() {
		ReservaResponse response;
		ReservaRequets requets = new ReservaRequets(); 
		requets.setNombres("Harry");
		requets.setApellidos("Mamani");
		requets.setCantidadPersonas(2);
		requets.setTipoHabitacion("NOR");
		
		try {
			when(habitacionDao.findByTipoAndEstado(anyString(), anyString())).thenReturn(lista);
			when(reservaDao.save(any())).thenReturn(new Reserva());
			response = reservaService.crearReserva(requets);
			Mockito.verify(habitacionDao, times(1)).cambiarEstado(anyString(), anyString());
		} catch (HotelException e) {
			response = null;
		}
		
		assertNotNull(response);
	}

}
