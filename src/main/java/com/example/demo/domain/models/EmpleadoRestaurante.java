package com.example.demo.domain.models;

public class EmpleadoRestaurante {

    private final Long id;

    private final Long idEmpleado; 

    private final Long idPropietario;

	public EmpleadoRestaurante(Long id, Long idEmpleado, Long idPropietario) {
		this.id = id;
		this.idEmpleado = idEmpleado;
		this.idPropietario = idPropietario;
	}

	public EmpleadoRestaurante() {
		this.id = null;
		this.idEmpleado = null;
		this.idPropietario = null;
	}

	public Long getId() {
		return id;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public Long getIdPropietario() {
		return idPropietario;
	}
}
