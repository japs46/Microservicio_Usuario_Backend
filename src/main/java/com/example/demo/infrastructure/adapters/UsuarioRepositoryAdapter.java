package com.example.demo.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;
import com.example.demo.infrastructure.entities.UsuarioEntity;
import com.example.demo.infrastructure.mappers.UsuarioMapper;
import com.example.demo.infrastructure.repositories.UsuarioEntityRepository;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort{
	
	private final UsuarioEntityRepository usuarioEntityRepository;
	
	public UsuarioRepositoryAdapter(UsuarioEntityRepository usuarioEntityRepository) {
		this.usuarioEntityRepository = usuarioEntityRepository;
	}

	@Override
	public Usuario save(Usuario usuario) {
		UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);
		return UsuarioMapper.toDomain(usuarioEntityRepository.save(usuarioEntity));
	}

	@Override
	public Usuario findById(Long id) {
		return UsuarioMapper.toDomain(usuarioEntityRepository.findById(id).orElseThrow());
	}

}
