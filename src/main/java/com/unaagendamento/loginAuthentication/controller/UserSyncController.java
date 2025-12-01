package com.unaagendamento.loginAuthentication.controller;

import com.unaagendamento.loginAuthentication.dto.UserSyncDTO;
import com.unaagendamento.loginAuthentication.entity.UserCredentials;
import com.unaagendamento.loginAuthentication.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/sync") // Rota especial para comunicação interna
public class UserSyncController {

    @Autowired
    private UserCredentialsRepository repository;

    @PostMapping("/users")
    public ResponseEntity<?> receberNovoUsuario(@RequestBody UserSyncDTO dadosRecebidos) {
        
        System.out.println("Sincronização: Recebido novo usuário -> " + dadosRecebidos.getEmail());

        // 1. Verifica se já existe para não dar erro de duplicidade
        if (repository.findByEmail(dadosRecebidos.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Email já existe no serviço de Login.");
        }

        // 2. Cria a nova entidade
        UserCredentials novoUsuario = new UserCredentials();
        
        // Gera um ID aleatório (já que é um novo registro neste banco)
        novoUsuario.setId(UUID.randomUUID().toString());
        
        novoUsuario.setEmail(dadosRecebidos.getEmail());
        

        // esta em criptografica ficaria assim se fosse usar -> novoUsuario.setSenha(passwordEncoder.encode(dadosRecebidos.getSenha()));
        novoUsuario.setSenha(dadosRecebidos.getSenha());

        // 3. Salva no banco
        repository.save(novoUsuario);

        return ResponseEntity.ok("Usuário sincronizado com sucesso!");
    }
}