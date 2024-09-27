package com.example.demo.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.infrastructure.entities.UsuarioEntity;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Long>{

	public Optional<UsuarioEntity> findByCorreo(String correo);
}
