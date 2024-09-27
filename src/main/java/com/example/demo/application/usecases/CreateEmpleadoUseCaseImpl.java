package com.example.demo.application.usecases;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreateEmpleadoUseCase;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@Component
public class CreateEmpleadoUseCaseImpl implements CreateEmpleadoUseCase {

	private final UsuarioRepositoryPort usuarioRepositoryPort;

	public CreateEmpleadoUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
		this.usuarioRepositoryPort = usuarioRepositoryPort;
	}

	@Override
	public Usuario createEmpledo(Usuario empelado) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Usuario empleadoNuevo = new Usuario(empelado.getId(), empelado.getNombre(), empelado.getApellido(),
				empelado.getDocumentoDeIdentidad(), empelado.getCelular(), empelado.getFechaNacimiento(),
				empelado.getCorreo(), passwordEncoder.encode(empelado.getClave()), Rol.EMPLEADO);

		return usuarioRepositoryPort.save(empleadoNuevo);
	}

}
