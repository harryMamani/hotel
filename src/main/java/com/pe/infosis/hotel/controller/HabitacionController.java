package com.pe.infosis.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitacionController {

	@GetMapping("/prueba")
	public ResponseEntity<String> prueba(){
		
		return ResponseEntity.ok("prueba");
	}
}
