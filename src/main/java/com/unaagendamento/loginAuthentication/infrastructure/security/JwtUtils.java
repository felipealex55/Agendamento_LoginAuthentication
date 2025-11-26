package com.unaagendamento.loginAuthentication.infrastructure.security;

import com.unaagendamento.loginAuthentication.business.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Cria a chave criptográfica
    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // GERAR TOKEN
    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userPrincipal) {
        return Jwts.builder()
        .subject(userPrincipal.getUsername())
        .issuedAt(new Date())
        .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(getSignInKey(), Jwts.SIG.HS256)
        .compact();
    }

    // PEGAR USUÁRIO
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }

    // VALIDAR TOKEN
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("Assinatura inválida: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("String vazia: " + e.getMessage());
        }
        return false;
    }
}