package com.example.demo.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.PropietarioService;
import com.example.demo.domain.models.Propietario;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
	
	private final PropietarioService propietarioService;

	public PropietarioController(PropietarioService propietarioService) {
		this.propietarioService = propietarioService;
	}

	@PostMapping("/guardar")
	public ResponseEntity<?> guardarPropietario(@RequestBody Propietario propietario){
		
		Propietario propietarioBd = propietarioService.createPropietario(propietario);
		
		return ResponseEntity.ok(propietarioBd);
		
	}
}
