package com.example.demo.domain.ports.out;

import java.util.Optional;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;

public interface UsuarioRepositoryPort {

	public Usuario save(Usuario usuario);
	
	public Optional<Usuario> findById(Long id);
	
	public Optional<UsuarioLogin> findUsuarioLoginByCorreo(String correo);
}
