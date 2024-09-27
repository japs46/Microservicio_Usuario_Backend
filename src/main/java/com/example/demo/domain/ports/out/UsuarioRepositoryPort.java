package com.example.demo.domain.ports.out;

import java.util.Optional;

import com.example.demo.domain.models.Usuario;

public interface UsuarioRepositoryPort {

	public Usuario save(Usuario usuario);
	
	public Optional<Usuario> findById(Long id);
	
	public Optional<Usuario> findByCorreo(String correo);
}
