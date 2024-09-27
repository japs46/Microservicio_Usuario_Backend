package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;
import com.example.demo.domain.ports.in.RetrieveUsuarioUseCase;

@Service
public class UsuarioService implements CreatePropietarioUseCase,RetrieveUsuarioUseCase{
	
	private final CreatePropietarioUseCase createPropietarioUseCase;
	private final RetrieveUsuarioUseCase retrieveUsuarioUseCase;

	public UsuarioService(CreatePropietarioUseCase createPropietarioUseCase,RetrieveUsuarioUseCase retrieveUsuarioUseCase) {
		this.createPropietarioUseCase = createPropietarioUseCase;
		this.retrieveUsuarioUseCase = retrieveUsuarioUseCase;
	}

	@Override
	public Usuario createPropietario(Usuario usuario) {
		return createPropietarioUseCase.createPropietario(usuario);
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
