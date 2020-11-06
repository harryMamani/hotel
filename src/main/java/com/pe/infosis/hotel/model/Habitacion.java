package com.pe.infosis.hotel.model;

public class Habitacion {

	private String puerta;
	
	private Integer piso;
	
	private TipoHabitacion tipo;
	
	private EstadoHabitacion estado;

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}

	public EstadoHabitacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoHabitacion estado) {
		this.estado = estado;
	}

	
	
}
