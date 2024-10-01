package com.example.demo.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@SpringBootTest(properties = {
	    "eureka.client.enabled=false"
	})
public class CreateClienteUseCaseImplTest {

	@MockBean
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private CreateClienteUseCaseImpl createClienteUseCase;

    private BCryptPasswordEncoder passwordEncoder;
    
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Test
    void testCreateCliente_Success() {

    	Usuario cliente = new Usuario(null, "John", "Doe", "12345678", "1234567890", 
                                      null, "johndoe@example.com", "clave123", Rol.CLIENTE);
        
        Usuario clienteGuardado = new Usuario(1L, "John", "Doe", "12345678", "1234567890", 
                                              null, "johndoe@example.com", passwordEncoder.encode("clave123"), Rol.CLIENTE);

        when(usuarioRepositoryPort.save(any(Usuario.class))).thenReturn(clienteGuardado);

        Usuario result = createClienteUseCase.createCliente(cliente);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getClave()).isNotEqualTo("clave123");
        assertThat(result.getRol()).isEqualTo(Rol.CLIENTE);

        verify(usuarioRepositoryPort).save(any(Usuario.class));
    }
}
