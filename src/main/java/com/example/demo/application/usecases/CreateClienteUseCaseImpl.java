package com.example.demo.application.usecases;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreateClienteUseCase;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@Component
public class CreateClienteUseCaseImpl implements CreateClienteUseCase {

	private final UsuarioRepositoryPort usuarioRepositoryPort;

	public CreateClienteUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
		this.usuarioRepositoryPort = usuarioRepositoryPort;
	}

	@Override
	public Usuario createCliente(Usuario cliente) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Usuario clienteNuevo = new Usuario(cliente.getId(), cliente.getNombre(), cliente.getApellido(),
				cliente.getDocumentoDeIdentidad(), cliente.getCelular(), cliente.getFechaNacimiento(),
				cliente.getCorreo(), passwordEncoder.encode(cliente.getClave()), Rol.CLIENTE);

		return usuarioRepositoryPort.save(clienteNuevo);
	}

}
