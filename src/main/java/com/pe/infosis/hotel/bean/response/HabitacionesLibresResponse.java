package com.pe.infosis.hotel.bean.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pe.infosis.hotel.bean.HabitacionDisponible;

public class HabitacionesLibresResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HabitacionDisponible> habitaciones;

	public List<HabitacionDisponible> getHabitaciones() {
		if(habitaciones == null) {
			habitaciones = new ArrayList<HabitacionDisponible>();
		}
		return habitaciones;
	}

	public void setHabitaciones(List<HabitacionDisponible> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
