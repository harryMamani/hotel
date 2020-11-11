package com.pe.infosis.hotel.bean.requets;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ReservaRequets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank (message="nombres no pueden ser nulo o vacio")
	private String nombres;
	
	@NotBlank (message="apellidos no pueden ser nulo o vacio")
	private String apellidos;
	
	@NotBlank (message="tipoHabitacion no puede ser nulo o vacio")
	private String tipoHabitacion;
	
	@Min(value= 1, message = "cantidad minima de personas es 1")
	private Integer cantidadPersonas;
	
	@Override
	public String toString() {
		return "ReservaRequets [nombres=" + nombres + ", apellidos=" + apellidos + ", tipoHabitacion=" + tipoHabitacion
				+ ", cantidadPersonas=" + cantidadPersonas + "]";
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	
	
}
