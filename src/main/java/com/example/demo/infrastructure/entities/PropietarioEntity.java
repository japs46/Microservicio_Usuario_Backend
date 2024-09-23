package com.example.demo.infrastructure.entities;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "propietario")
public class PropietarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	@Column(nullable = false)
	private final String nombre;
	
	@Column(nullable = false)
	private final String apellido;
	
	@Column(name = "documento_identidad", nullable = false)
	private final String documentoDeIdentidad;
	
	@Column(length = 13, nullable = false)
    private String celular;
	
	@Column(name = "fecha_nacimiento", nullable = false)
	private final LocalDate fechaNacimiento;
	
	@Column(nullable = false)
	private final String correo;
	
	@Column(name = "clave", nullable = false)
	private final String claveEncriptada;
	
	public PropietarioEntity() {
		this.id = null;
		this.nombre = "";
		this.apellido = "";
		this.documentoDeIdentidad = "";
		this.fechaNacimiento = null;
		this.correo = "";
		this.claveEncriptada = "";
	}

	public PropietarioEntity(Long id, String nombre, String apellido, String documentoDeIdentidad, String celular,
			LocalDate fechaNacimiento, String correo, String claveEncriptada) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documentoDeIdentidad = documentoDeIdentidad;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.claveEncriptada = encriptarClave(claveEncriptada);
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
