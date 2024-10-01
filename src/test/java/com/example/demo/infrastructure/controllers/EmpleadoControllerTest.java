package com.example.demo.infrastructure.controllers;

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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.services.EmpleadoService;
import com.example.demo.domain.models.Rol;
import com.example.demo.domain.models.Usuario;
import com.example.demo.infrastructure.config.SecurityConfig;
import com.example.demo.infrastructure.filters.MyUserDetailsService;
import com.example.demo.infrastructure.providers.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmpleadoController.class)
@Import(SecurityConfig.class)
public class EmpleadoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpleadoService empleadoService;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;

	public ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper=new ObjectMapper();
		
		when(jwtTokenProvider.validateToken(any(String.class), any(UserDetails.class))).thenReturn(true);
        when(jwtTokenProvider.getUsernameFromToken(any(String.class))).thenReturn("johndoe");
        
        UserDetails userDetails = User.withUsername("johndoe")
            .password("password123")
            .roles("PROPIETARIO")
            .build();
        when(myUserDetailsService.loadUserByUsername("johndoe")).thenReturn(userDetails);
	}
	
	@Test
    void testGuardarEmpleado_Success() throws Exception {
		String fechaStr = "2001-09-24"; 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = formato.parse(fechaStr);
        Usuario usuario = new Usuario(1L, "John", "Doe", "12345678", "1234567890", fecha, "johndoe@example.com", "clave123",Rol.EMPLEADO);

        when(empleadoService.createEmpledo(any(Usuario.class))).thenReturn(usuario);
        
        String token = "Bearer your-valid-jwt-token";

        mockMvc.perform(post("/api/empleados/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario))
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("John"));
    }

    @Test
    void testGuardarEmpleado_InternalServerError() throws Exception {
        when(empleadoService.createEmpledo(any(Usuario.class))).thenThrow(new RuntimeException("Error al crear el empleado"));

        Usuario propietario = new Usuario(1L, "John", "Doe", "12345678", "1234567890", new Date() , "johndoe@example.com", "clave123",Rol.EMPLEADO);

        String token = "Bearer your-valid-jwt-token";
        
        mockMvc.perform(post("/api/empleados/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propietario))
                .header("Authorization", token))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("Error al crear el empleado"));
    }
}
