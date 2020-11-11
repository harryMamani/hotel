package com.pe.infosis.hotel.bean.requets;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CrearHabitacionRequets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank (message="puerta no puede ser nulo o vacio")
	private String puerta;
	
	@NotBlank (message="piso no puede ser nulo o vacio")
	private String piso;
	
	@NotBlank(message="tipo no puede ser nulo o vacio")
	private String tipo;
	
	@NotBlank (message="estado no puede ser nulo o vacio")
	private String estado;
	
	

	@Override
	public String toString() {
		return "CrearHabitacionRequets [puerta=" + puerta + ", piso=" + piso + ", tipo=" + tipo + ", estado=" + estado + "]";
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
