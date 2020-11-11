package com.pe.infosis.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.pe.infosis.hotel.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
}
