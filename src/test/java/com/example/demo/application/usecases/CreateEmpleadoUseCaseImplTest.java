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
public class CreateEmpleadoUseCaseImplTest {

	@MockBean
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private CreateEmpleadoUseCaseImpl createEmpleadoUseCase;

    private BCryptPasswordEncoder passwordEncoder;
    
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Test
    void testCreateEmpleado_Success() {
    	
        Usuario empleado = new Usuario(null, "John", "Doe", "12345678", "1234567890", 
                                      null, "johndoe@example.com", "clave123", Rol.EMPLEADO);
        
        Usuario empleadoGuardado = new Usuario(1L, "John", "Doe", "12345678", "1234567890", 
                                              null, "johndoe@example.com", passwordEncoder.encode("clave123"), Rol.EMPLEADO);

        when(usuarioRepositoryPort.save(any(Usuario.class))).thenReturn(empleadoGuardado);

        Usuario result = createEmpleadoUseCase.createEmpledo(empleado);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getClave()).isNotEqualTo("clave123");
        assertThat(result.getRol()).isEqualTo(Rol.EMPLEADO);

        verify(usuarioRepositoryPort).save(any(Usuario.class));
    }
}
