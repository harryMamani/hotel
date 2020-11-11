package com.pe.infosis.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pe.infosis.hotel.bean.HabitacionDisponible;
import com.pe.infosis.hotel.entity.Habitacion;

public interface HabitacionRepository extends CrudRepository<Habitacion, String>{

	@Query("select h from Habitacion h where h.estado.codigo = :estado")
	public List<Habitacion> findByEstado(@Param("estado") String estado);
	
	@Query("select h from Habitacion h where h.tipo.codigo = :tipo")
	public List<Habitacion> findByTipo(@Param("tipo") String tipo);
	
	@Query("select h from Habitacion h where h.tipo.codigo = :tipo and h.estado.codigo = :estado")
	public List<Habitacion> findByTipoAndEstado(@Param("tipo") String tipo, @Param("estado") String estado);
	
	@Modifying
	@Query("update Habitacion h set h.estado.codigo = :estado where h.puerta = :puerta")
	public void cambiarEstado(@Param("puerta") String puerta, @Param("estado") String estado);
	
	@Query("select count(h) from Habitacion h where h.tipo.codigo = :tipo and h.estado.codigo = :estado")
	public int countByTipoandEstado(@Param("tipo") String tipo, @Param("estado") String estado);
	
	@Query("select new com.pe.infosis.hotel.bean.HabitacionDisponible(h.tipo.descripcion, h.tipo.capacidad, count(h)) from Habitacion h where h.tipo.codigo = :tipo and h.estado.codigo = 'LIB' ")
	public HabitacionDisponible habitacionDisponible(@Param("tipo") String tipo);
}
