package com.example.demo.application.usecases;

import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.RetrieveUsuarioUseCase;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@Component
public class RetrieveUsuarioUseCaseImpl implements RetrieveUsuarioUseCase{
	
	private final UsuarioRepositoryPort propietarioRepositoryPort;

	public RetrieveUsuarioUseCaseImpl(UsuarioRepositoryPort propietarioRepositoryPort) {
		this.propietarioRepositoryPort = propietarioRepositoryPort;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return propietarioRepositoryPort.findById(id);
	}

}
