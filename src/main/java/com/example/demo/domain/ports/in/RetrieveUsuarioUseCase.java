package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;

public interface RetrieveUsuarioUseCase {

	public Usuario buscarPorId(Long id);
	
	public UsuarioLogin buscarUsuarioLoginPorCorreo(String correo);
}
