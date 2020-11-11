package com.pe.infosis.hotel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transicion_estado")
public class TransicionEstado {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "origen")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EstadoHabitacion origen;
	
	@JoinColumn(name = "fin")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EstadoHabitacion fin;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public EstadoHabitacion getOrigen() {
		return origen;
	}


	public void setOrigen(EstadoHabitacion origen) {
		this.origen = origen;
	}


	public EstadoHabitacion getFin() {
		return fin;
	}


	public void setFin(EstadoHabitacion fin) {
		this.fin = fin;
	}
	
	
}
