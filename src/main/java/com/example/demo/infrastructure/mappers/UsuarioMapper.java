package com.example.demo.infrastructure.mappers;

import com.example.demo.domain.models.Usuario;
import com.example.demo.infrastructure.entities.UsuarioEntity;

public class UsuarioMapper {

	public static Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(entity.getId(), entity.getNombre(),
        		entity.getApellido(), entity.getDocumentoDeIdentidad(),
        		entity.getCelular(), entity.getFechaNacimiento(),
        		entity.getCorreo(),entity.getClaveEncriptada(),entity.getRol());
    }

    public static UsuarioEntity toEntity(Usuario domain) {
        return new UsuarioEntity(domain.getId(), domain.getNombre(),
        		domain.getApellido(), domain.getDocumentoDeIdentidad(),
        		domain.getCelular(), domain.getFechaNacimiento(),
        		domain.getCorreo(),domain.getClaveEncriptada(),domain.getRol());
    }
    
}
