package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreateClienteUseCase;

@Service
public class ClienteService implements CreateClienteUseCase{
	
	private final CreateClienteUseCase createClienteUseCase;

	public ClienteService(CreateClienteUseCase createClienteUseCase) {
		this.createClienteUseCase = createClienteUseCase;
	}

	@Override
	public Usuario createCliente(Usuario cliente) {
		return createClienteUseCase.createCliente(cliente);
	}

}
