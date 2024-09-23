package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.services.PropietarioService;
import com.example.demo.domain.models.Propietario;
import com.example.demo.infrastructure.controllers.PropietarioController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PropietarioController.class)
public class PropietarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PropietarioService propietarioService;

	

	public ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
			objectMapper=new ObjectMapper();
	}

	 @Test
	    void testGuardarPropietario_Success() throws Exception {
	        Propietario propietario = new Propietario(1L, "John", "Doe", "12345678", "1234567890", new Date(), "johndoe@example.com", "clave123");

	        when(propietarioService.createPropietario(any(Propietario.class))).thenReturn(propietario);

	        mockMvc.perform(post("/api/propietarios/guardar")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(propietario)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(1L))
	                .andExpect(jsonPath("$.nombre").value("John"));
	    }

	    @Test
	    void testGuardarPropietario_InternalServerError() throws Exception {
	        when(propietarioService.createPropietario(any(Propietario.class))).thenThrow(new RuntimeException("Error al crear propietario"));

	        Propietario propietario = new Propietario(1L, "John", "Doe", "12345678", "1234567890", new Date() , "johndoe@example.com", "clave123");

	        mockMvc.perform(post("/api/propietarios/guardar")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(propietario)))
	                .andExpect(status().isInternalServerError())
	                .andExpect(jsonPath("$").value("Ocurrio un error en el servidor"));
	    }

}
