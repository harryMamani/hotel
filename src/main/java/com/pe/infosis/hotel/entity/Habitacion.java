package com.pe.infosis.hotel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habitacion")
public class Habitacion{

	@Id
	@Column(name = "puerta")
	private String puerta;
	
	@Column(name = "piso")
	private String piso;
	
	@JoinColumn(name = "tipo")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TipoHabitacion tipo;
	
	@JoinColumn(name = "estado")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EstadoHabitacion estado;

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
