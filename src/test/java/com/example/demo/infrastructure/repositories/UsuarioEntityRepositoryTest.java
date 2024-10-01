package com.example.demo.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.models.Rol;
import com.example.demo.infrastructure.entities.UsuarioEntity;
import com.example.demo.infrastructure.entities.UsuarioLoginEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
public class UsuarioEntityRepositoryTest {

	@Autowired
    private UsuarioEntityRepository usuarioEntityRepository;
	
//	@Test
//	void save() {
//		
//	}
	
	@Test
    void testSaveAndFindByCorreo() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(null, "John", "Doe", "12345678", "1234567890", 
        		new Date(), "johndoe@example.com", "clave123", Rol.CLIENTE);
        usuarioEntityRepository.save(usuarioEntity);

        Optional<UsuarioEntity> result = usuarioEntityRepository.findByCorreo("johndoe@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("John");
	}
	
	@Test
    void testFindUsuarioLoginByCorreo() {
       
		UsuarioEntity usuarioEntity = new UsuarioEntity(null, "John", "Doe", "12345678", "1234567890", 
				new Date(), "johndoe@example.com", "clave123", Rol.CLIENTE);
        usuarioEntityRepository.save(usuarioEntity);
        
        Optional<UsuarioLoginEntity> result = usuarioEntityRepository.findUsuarioLoginByCorreo("johndoe@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getCorreo()).isEqualTo("johndoe@example.com");
    }

    @Test
    void testFindByCorreo_NotFound() {
        
    	Optional<UsuarioEntity> result = usuarioEntityRepository.findByCorreo("notfound@example.com");

        assertThat(result).isNotPresent();
    }
}
