package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.services.ClienteService;
import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.infrastructure.controllers.ClienteController;
import com.example.demo.infrastructure.filters.JwtAuthenticationFilter;
import com.example.demo.infrastructure.providers.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	public ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
			objectMapper=new ObjectMapper();
	}
	
	@Test
    void testGuardarCliente_Success() throws Exception {
		String fechaStr = "2001-09-24"; 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = formato.parse(fechaStr);
        Usuario cliente = new Usuario(1L, "John", "Doe", "12345678", "1234567890", fecha, "johndoe@example.com", "clave123",Rol.CLIENTE);

        when(clienteService.createCliente(any(Usuario.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("John"));
    }

    @Test
    void testGuardarCliente_InternalServerError() throws Exception {
        when(clienteService.createCliente(any(Usuario.class))).thenThrow(new RuntimeException("Error al crear propietario"));

        Usuario cliente = new Usuario(1L, "John", "Doe", "12345678", "1234567890", new Date() , "johndoe@example.com", "clave123",Rol.CLIENTE);

        mockMvc.perform(post("/api/clientes/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("Ocurrio un error en el servidor"));
    }
}
