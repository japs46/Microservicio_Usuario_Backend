package com.example.demo.infrastructure.entities;

import java.util.Date;

import com.example.demo.domain.models.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {
	
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
	private final Date fechaNacimiento;
	
	@Column(nullable = false)
	private final String correo;
	
	@Column(name = "clave", nullable = false)
	private final String clave;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private Rol rol;
	
	public UsuarioEntity() {
		this.id = null;
		this.nombre = "";
		this.apellido = "";
		this.documentoDeIdentidad = "";
		this.fechaNacimiento = null;
		this.correo = "";
		this.clave = "";
	}

	public UsuarioEntity(Long id, String nombre, String apellido, String documentoDeIdentidad, String celular,
			Date fechaNacimiento, String correo, String clave,Rol rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documentoDeIdentidad = documentoDeIdentidad;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.clave = clave;
		this.rol = rol;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

	public Rol getRol() {
		return rol;
	}

}
