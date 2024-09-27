package com.example.demo.application.usecases;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@Component
public class CreatePropietarioUseCaseImpl implements CreatePropietarioUseCase {

	private final UsuarioRepositoryPort usuarioRepositoryPort;

	public CreatePropietarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
		this.usuarioRepositoryPort = usuarioRepositoryPort;
	}

	@Override
	public Usuario createPropietario(Usuario propietario) {
		
		int edad = calcularEdad(propietario.getFechaNacimiento());
		if (edad < 18) {
			throw new UnderageException("El propietario no puede ser menor de edad");
		}
		
		BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		
		Usuario propietarioNuevo = new Usuario(propietario.getId(), propietario.getNombre(),
				propietario.getApellido(), propietario.getDocumentoDeIdentidad(),
				propietario.getCelular(), propietario.getFechaNacimiento(),
				propietario.getCorreo(), passwordEncoder.encode(propietario.getClave()), Rol.PROPIETARIO);
		
		return usuarioRepositoryPort.save(propietarioNuevo);
	}

	private int calcularEdad(Date fechaNacimiento) {
		LocalDate localDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate today = LocalDate.now();

		return Period.between(localDate, today).getYears();
	}

}
