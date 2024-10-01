package com.example.demo.application.usecases;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
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
		
	    if (id == null) {
	        throw new IllegalArgumentException("El ID no puede ser nulo.");
	    }
	    if (id <= 0) {
	        throw new IllegalArgumentException("El ID debe ser un número positivo.");
	    }
		
		return propietarioRepositoryPort.findById(id)
				.orElseThrow(()-> new NoSuchElementException("No se encontro ningun Usuario con el id: "+id));
	}

	@Override
	public UsuarioLogin buscarUsuarioLoginPorCorreo(String correo) {
		
		if (correo == null) {
	        throw new IllegalArgumentException("El Correo no puede ser nulo.");
	    }
		
		if (correo.trim().isEmpty()) {
			throw new IllegalArgumentException("El Correo no puede ser vacio.");
		}
		
		return propietarioRepositoryPort.findUsuarioLoginByCorreo(correo)
				.orElseThrow(()-> new NoSuchElementException("No se encontro ningun Usuario con el correo: "+correo));
	}

}
