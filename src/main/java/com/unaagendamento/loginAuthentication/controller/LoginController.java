package com.unaagendamento.loginAuthentication.controller;

import com.unaagendamento.loginAuthentication.business.UserDetailsImpl;
import com.unaagendamento.loginAuthentication.dto.LoginRequestDTO;
import com.unaagendamento.loginAuthentication.dto.LoginResponseDTO;
import com.unaagendamento.loginAuthentication.infrastructure.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {

        // 1. Tenta autenticar (vai chamar o UserDetailsServiceImpl e verificar a senha)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

        // 2. Se deu certo, salva no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Gera o Token JWT
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateTokenFromUserDetailsImpl(userDetails);

        // 4. Retorna o Token para o usuário
        return ResponseEntity.ok(new LoginResponseDTO(jwt));
    }
}