package com.example.demo.infrastructure.mappers;

import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.infrastructure.entities.UsuarioLoginEntity;

public class UsuarioLoginMapper {

	public static UsuarioLogin toDomain(UsuarioLoginEntity entity) {
        return new UsuarioLogin(entity.getCorreo(),entity.getRol());
    }

    public static UsuarioLoginEntity toEntity(UsuarioLogin domain) {
    	return new UsuarioLoginEntity(domain.getCorreo(),domain.getRol());
    }
    
}
