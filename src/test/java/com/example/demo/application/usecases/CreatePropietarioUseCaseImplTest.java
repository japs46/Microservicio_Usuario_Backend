package com.example.demo.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@SpringBootTest(properties = {
	    "eureka.client.enabled=false"
	})
public class CreatePropietarioUseCaseImplTest {

	@MockBean
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private CreatePropietarioUseCaseImpl createPropietarioUseCase;

    private BCryptPasswordEncoder passwordEncoder;
    
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Test
    void testCreatePropietario_Success() throws Exception {
        
        String fechaStr = "2000-09-24"; 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = formato.parse(fechaStr);

        Usuario propietario = new Usuario(null, "John", "Doe", "12345678", "1234567890",
                fechaNacimiento, "johndoe@example.com", "clave123", Rol.PROPIETARIO);

        Usuario propietarioGuardado = new Usuario(1L, "John", "Doe", "12345678", "1234567890",
                fechaNacimiento, "johndoe@example.com", passwordEncoder.encode("clave123"), Rol.PROPIETARIO);

        when(usuarioRepositoryPort.save(any(Usuario.class))).thenReturn(propietarioGuardado);

        Usuario result = createPropietarioUseCase.createPropietario(propietario);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getClave()).isNotEqualTo("clave123"); 
        assertThat(result.getRol()).isEqualTo(Rol.PROPIETARIO);

        verify(usuarioRepositoryPort).save(any(Usuario.class));
    }
    
    @Test
    void testCreatePropietario_Underage() throws Exception {

    	String fechaStr = "2010-09-24";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = formato.parse(fechaStr);

        Usuario propietario = new Usuario(null, "Jane", "Doe", "12345679", "1234567891",
                fechaNacimiento, "janedoe@example.com", "clave456", Rol.PROPIETARIO);

        assertThatExceptionOfType(UnderageException.class)
            .isThrownBy(() -> createPropietarioUseCase.createPropietario(propietario))
            .withMessage("El propietario no puede ser menor de edad");
    }
}
