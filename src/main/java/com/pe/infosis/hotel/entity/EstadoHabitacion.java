package com.pe.infosis.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_habitacion")
public class EstadoHabitacion {

	@Id
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public EstadoHabitacion() {
		
	}
	
	public EstadoHabitacion(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public EstadoHabitacion(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
