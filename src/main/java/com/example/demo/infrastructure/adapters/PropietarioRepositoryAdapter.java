package com.example.demo.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.out.PropietarioRepositoryPort;
import com.example.demo.infrastructure.entities.UsuarioEntity;
import com.example.demo.infrastructure.mappers.PropietarioMapper;
import com.example.demo.infrastructure.repositories.PropietarioEntityRepository;

@Repository
public class PropietarioRepositoryAdapter implements PropietarioRepositoryPort{
	
	private final PropietarioEntityRepository propietarioEntityRepository;
	
	public PropietarioRepositoryAdapter(PropietarioEntityRepository propietarioEntityRepository) {
		this.propietarioEntityRepository = propietarioEntityRepository;
	}

	@Override
	public Usuario save(Usuario propietario) {
		UsuarioEntity propietarioEntity = PropietarioMapper.toEntity(propietario);
		return PropietarioMapper.toDomain(propietarioEntityRepository.save(propietarioEntity));
	}

}
