package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Usuario;

public interface RetrieveUsuarioUseCase {

	public Usuario buscarPorId(Long id);
}
