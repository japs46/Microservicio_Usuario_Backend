package com.example.demo.infrastructure.adapters.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.domain.models.EmpleadoRestaurante;

@FeignClient(name = "Microservicio-Plazoleta")
public interface EmpleadoRestauranteFeignClient {

	@PostMapping("/api/empleadosRestaurante/guardar/{idEmpleado}")
	public EmpleadoRestaurante guardarEmpleadoRestaurante(@PathVariable Long idEmpleado, @RequestHeader("Authorization") String token);
	
}
