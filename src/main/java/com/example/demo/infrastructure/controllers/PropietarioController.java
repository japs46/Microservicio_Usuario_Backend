package com.example.demo.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.PropietarioService;
import com.example.demo.domain.models.Propietario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
	
	private final PropietarioService propietarioService;

	public PropietarioController(PropietarioService propietarioService) {
		this.propietarioService = propietarioService;
	}

	@Operation(summary = "Crear un nuevo Propietario", description = "Guarda un nuevo propietario en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Propietario guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public ResponseEntity<?> guardarPropietario(@RequestBody Propietario propietario){
		
		Propietario propietarioBd = propietarioService.createPropietario(propietario);
		
		return ResponseEntity.ok(propietarioBd);
		
	}
}
