package com.example.demo.application.usecases;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreateEmpleadoUseCase;
import com.example.demo.domain.ports.out.EmpleadoRestauranteExternalServicePort;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

import jakarta.transaction.Transactional;

@Component
public class CreateEmpleadoUseCaseImpl implements CreateEmpleadoUseCase {

	private final UsuarioRepositoryPort usuarioRepositoryPort;
	
	private final EmpleadoRestauranteExternalServicePort empleadoRestauranteExternalServicePort;

	public CreateEmpleadoUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort,EmpleadoRestauranteExternalServicePort empleadoRestauranteExternalServicePort) {
		this.usuarioRepositoryPort = usuarioRepositoryPort;
		this.empleadoRestauranteExternalServicePort = empleadoRestauranteExternalServicePort;
	}

	@Override
	@Transactional
	public Usuario createEmpleado(Usuario empleado,String token) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Usuario empleadoNuevo = new Usuario(empleado.getId(), empleado.getNombre(), empleado.getApellido(),
				empleado.getDocumentoDeIdentidad(), empleado.getCelular(), empleado.getFechaNacimiento(),
				empleado.getCorreo(), passwordEncoder.encode(empleado.getClave()), Rol.EMPLEADO);

		Usuario empleadoBd = usuarioRepositoryPort.save(empleadoNuevo);
		
		empleadoRestauranteExternalServicePort.guardarEmpleadoRestaurante(empleadoBd.getId(), token);
		
		return empleadoBd;
	}

}
