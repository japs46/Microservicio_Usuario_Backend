package com.example.demo.infrastructure.entities;

import com.example.demo.domain.models.Rol;

public class UsuarioLoginEntity {

	private final String correo;
	
	private final Rol rol;

	public UsuarioLoginEntity(String correo, Rol rol) {
		this.correo = correo;
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public Rol getRol() {
		return rol;
	}
}
