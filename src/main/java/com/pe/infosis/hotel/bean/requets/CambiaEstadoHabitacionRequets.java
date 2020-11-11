package com.pe.infosis.hotel.bean.requets;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CambiaEstadoHabitacionRequets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank (message="puerta no puede ser nulo o vacio")
	private String puerta;
	
	@NotBlank (message="estado no puede ser nulo o vacio")
	private String estado;

	@Override
	public String toString() {
		return "CambiaEstadoHabitacionRequets [puerta=" + puerta + ", estado=" + estado + "]";
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
