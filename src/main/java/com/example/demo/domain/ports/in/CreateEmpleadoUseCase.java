package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Usuario;

public interface CreateEmpleadoUseCase {

	public Usuario createEmpleado(Usuario empleado,String token);
}
