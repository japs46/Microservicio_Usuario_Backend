package com.example.demo.infrastructure.providers;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.Usuario;
import com.example.demo.infrastructure.mappers.UsuarioMapper;
import com.example.demo.infrastructure.repositories.UsuarioEntityRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

	private final SecretKey key = new SecretKeySpec("mi_clave_secreta_super_segura_y_larga".getBytes(), "HmacSHA256");

    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10;
    
    private final UsuarioEntityRepository usuarioEntityRepository;

    public JwtTokenProvider(UsuarioEntityRepository usuarioEntityRepository) {
		this.usuarioEntityRepository = usuarioEntityRepository;
	}

	public String generateToken(Authentication authentication) {
    	
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	
    	Usuario usuario = usuarioEntityRepository.findByCorreo(userDetails.getUsername()).map(UsuarioMapper::toDomain)
    			.orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con el correo: " + userDetails.getUsername()));
    	
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", roles)
                .claim("idUser", usuario.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
