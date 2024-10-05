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
	public Usuario createEmpleado(Usuario empleado,String token) {
		return createEmpleadoUseCase.createEmpleado(empleado,token);
	}

}
