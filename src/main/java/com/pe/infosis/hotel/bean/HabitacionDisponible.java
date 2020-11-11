package com.pe.infosis.hotel.bean;

import java.io.Serializable;

public class HabitacionDisponible implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoHabitacion;
	
	private int capacidadHabitacion;
	
	private long habitacionesDisponibles;
	
	public HabitacionDisponible() {
		
	}

	public HabitacionDisponible(String tipoHabitacion, int capacidadHabitacion, long habitacionesDisponibles) {
		this.tipoHabitacion = tipoHabitacion;
		this.capacidadHabitacion = capacidadHabitacion;
		this.habitacionesDisponibles = habitacionesDisponibles;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public int getCapacidadHabitacion() {
		return capacidadHabitacion;
	}

	public void setCapacidadHabitacion(int capacidadHabitacion) {
		this.capacidadHabitacion = capacidadHabitacion;
	}

	public long getHabitacionesDisponibles() {
		return habitacionesDisponibles;
	}

	public void setHabitacionesDisponibles(long habitacionesDisponibles) {
		this.habitacionesDisponibles = habitacionesDisponibles;
	}

	
	
}
