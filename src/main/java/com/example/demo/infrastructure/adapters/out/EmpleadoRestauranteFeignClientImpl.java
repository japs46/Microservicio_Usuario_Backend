package com.example.demo.infrastructure.adapters.out;

import org.springframework.stereotype.Component;

import com.example.demo.domain.models.EmpleadoRestaurante;
import com.example.demo.domain.ports.out.EmpleadoRestauranteExternalServicePort;

@Component
public class EmpleadoRestauranteFeignClientImpl implements EmpleadoRestauranteExternalServicePort{
	
	private final EmpleadoRestauranteFeignClient empleadoRestauranteFeignClient;
	
	public EmpleadoRestauranteFeignClientImpl(EmpleadoRestauranteFeignClient empleadoRestauranteFeignClient) {
		this.empleadoRestauranteFeignClient = empleadoRestauranteFeignClient;
	}

	@Override
	public EmpleadoRestaurante guardarEmpleadoRestaurante(Long idEmpleado,String token) {
		return empleadoRestauranteFeignClient.guardarEmpleadoRestaurante(idEmpleado, token);
	}

}
