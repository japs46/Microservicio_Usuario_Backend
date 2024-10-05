package com.example.demo.infrastructure.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.EmpleadoService;
import com.example.demo.domain.exception.UnderageException;
import com.example.demo.domain.models.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	private Logger LOGGUER= LoggerFactory.getLogger(EmpleadoController.class);
	
	private  EmpleadoService empleadoService;

	public EmpleadoController(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	@Operation(summary = "Crear un nuevo empleado", description = "Guarda un nuevo empleado en la base de datos.")
    @ApiResponse(responseCode = "200", description = "empleado guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Usuario empleado, HttpServletRequest request){
		
		try {
			LOGGUER.info("Inicio Creacion de empleado");
			String authHeader = request.getHeader("Authorization");
			Usuario empleadoBd = empleadoService.createEmpleado(empleado,authHeader);
			
			return ResponseEntity.ok(empleadoBd);
			
		} catch (UnderageException e) {
			LOGGUER.error("Ocurrio un problema: "+e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}catch (Exception e) {
			LOGGUER.error("Ocurrio un error, descripcion del error: "+e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		
	}
}
