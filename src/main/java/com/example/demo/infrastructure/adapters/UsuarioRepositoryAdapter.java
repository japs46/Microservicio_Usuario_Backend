package com.example.demo.infrastructure.adapters;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;
import com.example.demo.infrastructure.entities.UsuarioEntity;
import com.example.demo.infrastructure.mappers.UsuarioLoginMapper;
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
	public Optional<Usuario> findById(Long id) {
		Optional<UsuarioEntity> usuario = usuarioEntityRepository.findById(id);
		return usuario.map(UsuarioMapper::toDomain);
	}

	@Override
	public Optional<UsuarioLogin> findUsuarioLoginByCorreo(String correo) {
		return usuarioEntityRepository.findUsuarioLoginByCorreo(correo).map(UsuarioLoginMapper::toDomain);
	}

}
