package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreateEmpleadoUseCase;

@Service
public class EmpleadoService implements CreateEmpleadoUseCase{
	
	private final CreateEmpleadoUseCase createEmpleadoUseCase;

	public EmpleadoService(CreateEmpleadoUseCase createEmpleadoUseCase) {
		this.createEmpleadoUseCase = createEmpleadoUseCase;
	}

	@Override
	public Usuario createEmpledo(Usuario empelado) {
		return createEmpleadoUseCase.createEmpledo(empelado);
	}

}
