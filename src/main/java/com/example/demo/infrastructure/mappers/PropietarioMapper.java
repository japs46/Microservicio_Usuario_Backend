package com.example.demo.infrastructure.mappers;

import com.example.demo.domain.models.Propietario;
import com.example.demo.infrastructure.entities.PropietarioEntity;

public class PropietarioMapper {

	public static Propietario toDomain(PropietarioEntity entity) {
        return new Propietario(entity.getId(), entity.getNombre(),
        		entity.getApellido(), entity.getDocumentoDeIdentidad(),
        		entity.getCelular(), entity.getFechaNacimiento(),
        		entity.getCorreo(),entity.getClaveEncriptada());
    }

    public static PropietarioEntity toEntity(Propietario domain) {
        return new PropietarioEntity(domain.getId(), domain.getNombre(),
        		domain.getApellido(), domain.getDocumentoDeIdentidad(),
        		domain.getCelular(), domain.getFechaNacimiento(),
        		domain.getCorreo(),domain.getClaveEncriptada());
    }
    
}
