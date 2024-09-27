package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;

@Service
public class PropietarioService implements CreatePropietarioUseCase{
	
	private final CreatePropietarioUseCase createPropietarioUseCase;
	
	public PropietarioService(CreatePropietarioUseCase createPropietarioUseCase) {
		this.createPropietarioUseCase = createPropietarioUseCase;
	}

	@Override
	public Usuario createPropietario(Usuario propietario) {
		return createPropietarioUseCase.createPropietario(propietario);
	}

}
