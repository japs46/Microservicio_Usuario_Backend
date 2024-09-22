package com.example.demo.application.usecases;

import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Propietario;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;
import com.example.demo.domain.ports.out.PropietarioRepositoryPort;

@Component
public class CreatePropietarioUseCaseImpl implements CreatePropietarioUseCase{

	private final PropietarioRepositoryPort propietarioRepositoryPort;

	public CreatePropietarioUseCaseImpl(PropietarioRepositoryPort propietarioRepositoryPort) {
		this.propietarioRepositoryPort = propietarioRepositoryPort;
	}

	@Override
	public Propietario createPropietario(Propietario propietario) {
		return propietarioRepositoryPort.save(propietario);
	}
	
}
