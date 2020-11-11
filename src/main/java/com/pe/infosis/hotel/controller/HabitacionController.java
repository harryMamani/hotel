package com.pe.infosis.hotel.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.infosis.hotel.bean.Status;
import com.pe.infosis.hotel.bean.requets.ActualizarHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CambiaEstadoHabitacionRequets;
import com.pe.infosis.hotel.bean.requets.CrearHabitacionRequets;
import com.pe.infosis.hotel.bean.response.HabitacionResponse;
import com.pe.infosis.hotel.bean.response.HabitacionesLibresResponse;
import com.pe.infosis.hotel.comun.Constantes;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;
import com.pe.infosis.hotel.exception.HotelException;
import com.pe.infosis.hotel.service.HabitacionService;

@Validated
@PreAuthorize("hasRole('"+Constantes.ROL_GERENTE+"')")
@RestController
@RequestMapping("/api/v1/habitaciones")
public class HabitacionController {

	@Autowired
	HabitacionService habitacionService;

	@PreAuthorize("hasRole('"+Constantes.ROL_RECIPCIONISTA+"') or hasRole('"+Constantes.ROL_GERENTE+"')")
	@GetMapping("/")
	public ResponseEntity<List<HabitacionResponse>> listar() {

		List<HabitacionResponse> lista = habitacionService.listar();

		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/")
	public ResponseEntity<Status> crear(@Valid @RequestBody CrearHabitacionRequets requets) throws HotelException {
		
		habitacionService.crear(requets);
		
		return ResponseEntity.ok(ErrorCodeEnum.OK.status());

	}
	
	@PutMapping("/{puerta}")
	public ResponseEntity<Status> actualizar(@Valid @RequestBody ActualizarHabitacionRequets requets,@NotBlank(message = "puerta no puede ser nulo o vacio") @PathVariable("puerta") String puerta) throws HotelException {
		
		habitacionService.actualizar(puerta, requets);
		
		return ResponseEntity.ok(ErrorCodeEnum.OK.status());

	}
	
	@DeleteMapping("/{puerta}")
	public ResponseEntity<Status> eliminar(@NotBlank(message = "puerta no puede ser nulo o vacio")@PathVariable("puerta") String puerta) throws HotelException {
		
		habitacionService.eliminar(puerta);
		
		return ResponseEntity.ok(ErrorCodeEnum.OK.status());

	}
	
	@PreAuthorize("hasRole('"+Constantes.ROL_RECIPCIONISTA+"') or hasRole('"+Constantes.ROL_GERENTE+"')")
	@GetMapping("/estado/{estado}")
	public ResponseEntity<List<HabitacionResponse>> listarXEstado(@NotBlank(message = "estado no puede ser nulo o vacio") @PathVariable("estado") String estado) {

		List<HabitacionResponse> lista = habitacionService.listarXEstado(estado);

		return ResponseEntity.ok(lista);
	}
	
	@PreAuthorize("hasRole('"+Constantes.ROL_RECIPCIONISTA+"') or hasRole('"+Constantes.ROL_GERENTE+"')")
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<HabitacionResponse>> listarXTipo(@NotBlank(message = "tipo no puede ser nulo o vacio") @PathVariable("tipo") String tipo) {

		List<HabitacionResponse> lista = habitacionService.listarXTipo(tipo);

		return ResponseEntity.ok(lista);
	}
	
	@PreAuthorize("hasRole('"+Constantes.ROL_RECIPCIONISTA+"') or hasRole('"+Constantes.ROL_GERENTE+"')")
	@PatchMapping("/cambiarEstado")
	public ResponseEntity<Status> cambiarEstado(@Valid @RequestBody CambiaEstadoHabitacionRequets requets) throws HotelException {
		
		habitacionService.cambiarEstado(requets);
		
		return ResponseEntity.ok(ErrorCodeEnum.OK.status());

	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("/libres")
	public ResponseEntity<HabitacionesLibresResponse> listarLibres() {

		HabitacionesLibresResponse response = habitacionService.habitacionesLibres();

		return ResponseEntity.ok(response);
	}
	
}
