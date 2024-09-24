package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.Usuario;

public interface UsuarioRepositoryPort {

	public Usuario save(Usuario usuario);
	
	public Usuario findById(Long id);
}
