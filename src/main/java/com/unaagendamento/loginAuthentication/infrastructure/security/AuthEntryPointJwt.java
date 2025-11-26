package com.unaagendamento.loginAuthentication.infrastructure.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        
        // 1. Loga o erro no console do Backend
        logger.error("Erro não autorizado: {}", authException.getMessage());

        // 2. Devolve o erro 401 para o Flutter
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro: Não autorizado");
    }
}