package com.unaagendamento.loginAuthentication.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Value;

@Component
public class AuthEntryPointJwt {
    
    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationsMs}")
    private int jwtExpirationsMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userData) {

    }
}
