package com.example.demo.infrastructure.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.ClienteService;
import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private Logger LOGGUER= LoggerFactory.getLogger(ClienteController.class);
	
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Operation(summary = "Crear un nuevo cliente", description = "Guarda un nuevo cliente en la base de datos.")
    @ApiResponse(responseCode = "200", description = "cliente guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Usuario cliente){
		
		try {
			LOGGUER.info("Inicio Creacion de cliente");
			
			Usuario clienteBd = clienteService.createCliente(cliente);
			
			return ResponseEntity.ok(clienteBd);
			
		} catch (UnderageException e) {
			LOGGUER.error("Ocurrio un problema: "+e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}catch (Exception e) {
			LOGGUER.error("Ocurrio un error, descripcion del error: "+e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		
	}
}
