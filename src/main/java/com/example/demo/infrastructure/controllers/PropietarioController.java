package com.example.demo.infrastructure.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.PropietarioService;
import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {
	
	private Logger LOGGUER= LoggerFactory.getLogger(PropietarioController.class);
	
//	@Autowired
	private  PropietarioService propietarioService;

	public PropietarioController(PropietarioService propietarioService) {
		this.propietarioService = propietarioService;
	}

	@Operation(summary = "Crear un nuevo Propietario", description = "Guarda un nuevo propietario en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Propietario guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public ResponseEntity<?> guardarPropietario(@Valid @RequestBody Usuario propietario){
		
		try {
			LOGGUER.info("Inicio Creacion de Propietario");
			Usuario propietarioBd = propietarioService.createPropietario(propietario);
			
			return ResponseEntity.ok(propietarioBd);
		} catch (UnderageException e) {
			LOGGUER.error("Ocurrio un problema: "+e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}catch (Exception e) {
			LOGGUER.error("Ocurrio un error, descripcion del error: "+e.getMessage());
			return ResponseEntity.internalServerError().body("Ocurrio un error en el servidor");
		}
		
	}
}
