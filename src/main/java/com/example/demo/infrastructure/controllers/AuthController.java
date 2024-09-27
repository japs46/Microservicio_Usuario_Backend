package com.example.demo.infrastructure.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.services.UsuarioService;
import com.example.demo.domain.models.UsuarioLogin;
import com.example.demo.infrastructure.providers.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private Logger LOGGUER= LoggerFactory.getLogger(UsuarioController.class);

	private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
    
    @Operation(summary = "Buscar un usuario por correo", description = "Busca un usuario en la base de datos por correo de usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario Encontrado exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@GetMapping("/buscarUsuarioLogin/{correo}")
	public ResponseEntity<?> buscarUsuarioLogin(@PathVariable String correo){
		try {
			LOGGUER.info("Iniciando Busqueda Usuario");
			UsuarioLogin usuarioLogin = usuarioService.buscarUsuarioLoginPorCorreo(correo);
			
			return ResponseEntity.ok(usuarioLogin);
		} catch (Exception e) {
			LOGGUER.error("Ocurrio un inconveniente al buscar por correo, descripcion del inconveniente: "+e.getMessage());
			return ResponseEntity.internalServerError().body("inconveniente buscar usuario por id: "+e.getMessage());
		}
	}
}
