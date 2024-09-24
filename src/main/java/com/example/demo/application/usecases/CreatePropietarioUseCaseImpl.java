package com.example.demo.application.usecases;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.in.CreatePropietarioUseCase;
import com.example.demo.domain.ports.out.PropietarioRepositoryPort;

@Component
public class CreatePropietarioUseCaseImpl implements CreatePropietarioUseCase {

	private final PropietarioRepositoryPort propietarioRepositoryPort;

	public CreatePropietarioUseCaseImpl(PropietarioRepositoryPort propietarioRepositoryPort) {
		this.propietarioRepositoryPort = propietarioRepositoryPort;
	}

	@Override
	public Usuario createPropietario(Usuario propietario) {
		
		int edad = calcularEdad(propietario.getFechaNacimiento());
		if (edad < 18) {
			throw new UnderageException("El propietario no puede ser menor de edad");
		}
		propietario.setRol(Rol.PROPIETARIO);
		return propietarioRepositoryPort.save(propietario);
	}

	private int calcularEdad(Date fechaNacimiento) {
		LocalDate localDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate today = LocalDate.now();

		return Period.between(localDate, today).getYears();
	}

}
