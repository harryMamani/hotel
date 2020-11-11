package com.pe.infosis.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pe.infosis.hotel.entity.TransicionEstado;

public interface TransicionEstadoRepository extends CrudRepository<TransicionEstado, Integer>{

	@Query("select t from TransicionEstado t where t.origen.codigo = :origen and t.fin.codigo = :fin")
	TransicionEstado findByOrigenAndFin(@Param("origen") String origen, @Param("fin") String fin);
}
