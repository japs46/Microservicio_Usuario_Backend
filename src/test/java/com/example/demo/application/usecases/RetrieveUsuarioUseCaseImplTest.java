package com.example.demo.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.domain.ports.out.UsuarioRepositoryPort;

@SpringBootTest(properties = {
	    "eureka.client.enabled=false"
	})
public class RetrieveUsuarioUseCaseImplTest {

	@MockBean
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private RetrieveUsuarioUseCaseImpl retrieveUsuarioUseCase;
    
    @Nested
    class BuscarPorIdTests {

        @Test
        void testBuscarPorId_Success() {
            Long id = 1L;
            Usuario usuario = new Usuario(id, "John", "Doe", "12345678", "1234567890", 
                                          null, "johndoe@example.com", "clave123", Rol.CLIENTE);
            when(usuarioRepositoryPort.findById(id)).thenReturn(Optional.of(usuario));

            Usuario result = retrieveUsuarioUseCase.buscarPorId(id);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(id);
            assertThat(result.getNombre()).isEqualTo("John");
        }
        
        @Test
        void testBuscarPorId_NotFound() {
            Long id = 1L;
            when(usuarioRepositoryPort.findById(id)).thenReturn(Optional.empty());

            assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarPorId(id))
                .withMessage("No se encontro ningun Usuario con el id: " + id);
        }
        
        @Test
        void testBuscarPorId_Null() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarPorId(null))
                .withMessage("El ID no puede ser nulo.");
        }
        
        @Test
        void testBuscarPorId_Vacio() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarPorId(-1L))
                .withMessage("El ID debe ser un número positivo.");
        }
    }
    
    @Nested
    class BuscarUsuarioLoginPorCorreoTests {

        @Test
        void testBuscarUsuarioLoginPorCorreo_Success() {
            String correo = "johndoe@example.com";
            UsuarioLogin usuarioLogin = new UsuarioLogin("johndoe@example.com", Rol.ADMIN);
            when(usuarioRepositoryPort.findUsuarioLoginByCorreo(correo)).thenReturn(Optional.of(usuarioLogin));

            UsuarioLogin result = retrieveUsuarioUseCase.buscarUsuarioLoginPorCorreo(correo);

            assertThat(result).isNotNull();
            assertThat(result.getCorreo()).isEqualTo(correo);
        }
        
        @Test
        void testBuscarUsuarioLoginPorCorreo_Null() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarUsuarioLoginPorCorreo(null))
                .withMessage("El Correo no puede ser nulo.");
        }
        
        @Test
        void testBuscarUsuarioLoginPorCorreo_Vacio() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarUsuarioLoginPorCorreo(" "))
                .withMessage("El Correo no puede ser vacio.");
        }
        
        @Test
        void testBuscarUsuarioLoginPorCorreo_NotFound() {
            String correo = "johndoe@example.com";
            when(usuarioRepositoryPort.findUsuarioLoginByCorreo(correo)).thenReturn(Optional.empty());

            assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> retrieveUsuarioUseCase.buscarUsuarioLoginPorCorreo(correo))
                .withMessage("No se encontro ningun Usuario con el correo: " + correo);
        }
    }
}
