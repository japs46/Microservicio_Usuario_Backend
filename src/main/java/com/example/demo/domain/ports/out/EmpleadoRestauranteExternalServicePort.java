package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.EmpleadoRestaurante;

public interface EmpleadoRestauranteExternalServicePort {

	public EmpleadoRestaurante guardarEmpleadoRestaurante(Long idEmpleado,String token); 
}
