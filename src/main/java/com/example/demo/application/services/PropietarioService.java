package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Propietario;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;

@Service
public class PropietarioService implements CreatePropietarioUseCase{
	
	private final CreatePropietarioUseCase createPropietarioUseCase;

	public PropietarioService(CreatePropietarioUseCase createPropietarioUseCase) {
		this.createPropietarioUseCase = createPropietarioUseCase;
	}

	@Override
	public Propietario createPropietario(Propietario propietario) {
		return createPropietarioUseCase.createPropietario(propietario);
	}

}
