package com.example.demo.infrastructure.filters;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.infrastructure.mappers.UsuarioMapper;
import com.example.demo.infrastructure.repositories.UsuarioEntityRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private Logger LOGGUER= LoggerFactory.getLogger(MyUserDetailsService.class);
	
	private final UsuarioEntityRepository usuaEntityRepository;
	
	public MyUserDetailsService(UsuarioEntityRepository usuaEntityRepository) {
		this.usuaEntityRepository = usuaEntityRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		
		try {
			LOGGUER.info("Iniciando verificación credenciales");
			
			Usuario usuario = usuaEntityRepository.findByCorreo(correo).map(UsuarioMapper::toDomain)
			        .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con el correo: " + correo));
			System.err.println(usuario.toString());
			List<GrantedAuthority> rol = new ArrayList<>();
			rol.add(new SimpleGrantedAuthority("ROLE_" +usuario.getRol().toString()));
			
			UserDetails userDetails= new User(usuario.getCorreo(), usuario.getClave(), rol);
			
			return userDetails;
			
		} catch (Exception e) {
			LOGGUER.info("Ocurrio un error en la verificacion de credenciales: "+e.getMessage());
			return null;
		}
		
	}

}
