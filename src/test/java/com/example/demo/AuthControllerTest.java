package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.services.UsuarioService;
import com.example.demo.infrastructure.config.SecurityConfig;
import com.example.demo.infrastructure.controllers.AuthController;
import com.example.demo.infrastructure.filters.MyUserDetailsService;
import com.example.demo.infrastructure.providers.JwtTokenProvider;


@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;

	@MockBean
	private UsuarioService usuarioService;
	
	@Test
    void testLogin_Success() throws Exception {
        String username = "user";
        String password = "password";
        String token = "jwt-token";

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(token);

        mockMvc.perform(post("/auth/login")
                .param("username", username)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().string(token));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(authentication);
    }
}
