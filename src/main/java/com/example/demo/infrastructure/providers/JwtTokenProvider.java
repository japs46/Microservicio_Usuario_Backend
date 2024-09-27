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
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

    // Clave secreta para firmar el token (generada automáticamente)
	private final SecretKey key = new SecretKeySpec("mi_clave_secreta_super_segura_y_larga".getBytes(), "HmacSHA256");

    // Duración del token (ej. 10 horas)
    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10;

    // Generar el token
    public String generateToken(Authentication authentication) {
    	
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", roles) 
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    // Obtener el nombre de usuario del token
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Validar el token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer fecha de expiración
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer un solo claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los claims del token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
