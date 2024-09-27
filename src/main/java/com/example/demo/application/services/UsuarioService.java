package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.domain.ports.in.RetrieveUsuarioUseCase;

@Service
public class UsuarioService implements RetrieveUsuarioUseCase{
	
	private final RetrieveUsuarioUseCase retrieveUsuarioUseCase;

	public UsuarioService(RetrieveUsuarioUseCase retrieveUsuarioUseCase) {
		this.retrieveUsuarioUseCase = retrieveUsuarioUseCase;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return retrieveUsuarioUseCase.buscarPorId(id);
	}

	@Override
	public UsuarioLogin buscarUsuarioLoginPorCorreo(String correo) {
		return retrieveUsuarioUseCase.buscarUsuarioLoginPorCorreo(correo);
	}

}
