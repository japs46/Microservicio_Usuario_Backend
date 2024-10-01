package com.example.demo.infrastructure.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;
import com.example.demo.infrastructure.entities.UsuarioEntity;
import com.example.demo.infrastructure.entities.UsuarioLoginEntity;
import com.example.demo.infrastructure.repositories.UsuarioEntityRepository;

@SpringBootTest(properties = {
	    "eureka.client.enabled=false"
	})
public class UsuarioRepositoryAdapterTest {

	@MockBean
    private UsuarioEntityRepository usuarioEntityRepository;
	
	@Autowired
	private UsuarioRepositoryPort usuarioRepositoryPort;
	
	@Test
    void testSave() {

		Usuario usuario = new Usuario(1L, "John", "Doe", "12345678", "1234567890", 
                                       null, "johndoe@example.com", "clave123", Rol.CLIENTE);
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "John", "Doe", "12345678", "1234567890", 
                                                         null, "johndoe@example.com", "clave123", Rol.CLIENTE);
        when(usuarioEntityRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario result = usuarioRepositoryPort.save(usuario);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(usuario.getId());
        assertThat(result.getNombre()).isEqualTo(usuario.getNombre());
    }
	
	 @Test
	    void testFindById_Success() {

		 	Long id = 1L;
	        UsuarioEntity usuarioEntity = new UsuarioEntity(id, "John", "Doe", "12345678", "1234567890", 
	                                                         null, "johndoe@example.com", "clave123", Rol.CLIENTE);
	        when(usuarioEntityRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

	        Optional<Usuario> result = usuarioRepositoryPort.findById(id);

	        assertThat(result).isPresent();
	        assertThat(result.get().getId()).isEqualTo(id);
	    }

	    @Test
	    void testFindById_NotFound() {

	    	Long id = 1L;
	        when(usuarioEntityRepository.findById(id)).thenReturn(Optional.empty());

	        Optional<Usuario> result = usuarioRepositoryPort.findById(id);

	        assertThat(result).isNotPresent();
	    }

	    @Test
	    void testFindUsuarioLoginByCorreo_Success() {

	    	String correo = "johndoe@example.com";
	        UsuarioLoginEntity usuarioLoginEntity = new UsuarioLoginEntity(correo, Rol.ADMIN);
	        when(usuarioEntityRepository.findUsuarioLoginByCorreo(correo)).thenReturn(Optional.of(usuarioLoginEntity));

	        Optional<UsuarioLogin> result = usuarioRepositoryPort.findUsuarioLoginByCorreo(correo);

	        assertThat(result).isPresent();
	        assertThat(result.get().getCorreo()).isEqualTo(correo);
	    }

	    @Test
	    void testFindUsuarioLoginByCorreo_NotFound() {

	    	String correo = "johndoe@example.com";
	        when(usuarioEntityRepository.findUsuarioLoginByCorreo(correo)).thenReturn(Optional.empty());

	        Optional<UsuarioLogin> result = usuarioRepositoryPort.findUsuarioLoginByCorreo(correo);

	        assertThat(result).isNotPresent();
	    }
}
