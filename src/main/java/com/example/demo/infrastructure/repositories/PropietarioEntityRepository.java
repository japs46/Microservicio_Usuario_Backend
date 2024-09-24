package com.example.demo.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.infrastructure.entities.UsuarioEntity;

@Repository
public interface PropietarioEntityRepository extends JpaRepository<UsuarioEntity, Long>{

}
