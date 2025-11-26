package com.unaagendamento.loginAuthentication.infrastructure.security;

import org.springframework.stereotype.Component;
import com.unaagendamento.loginAuthentication.business.UserDetailsImpl;
import lombok.Value;

@Component
public class JwtUtils {
    
    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationsMs}")
    private int jwtExpirationsMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userData) {

    }
}
