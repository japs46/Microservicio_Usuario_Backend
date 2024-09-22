package com.example.demo.domain.models;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Propietario {

	private final Long id;
	private final String nombre;
	private final String apellido;
	private final String documentoDeIdentidad;
	private final String celular;
	private final LocalDate fechaNacimiento;
	private final String correo;
	private final String claveEncriptada;

	public Propietario(Long id, String nombre, String apellido, String documentoDeIdentidad, String celular,
			LocalDate fechaNacimiento, String correo, String clave) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documentoDeIdentidad = documentoDeIdentidad;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.claveEncriptada = encriptarClave(clave);
	}

	private String encriptarClave(String clave) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(clave);
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDocumentoDeIdentidad() {
		return documentoDeIdentidad;
	}

	public String getCelular() {
		return celular;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClaveEncriptada() {
		return claveEncriptada;
	}
}
