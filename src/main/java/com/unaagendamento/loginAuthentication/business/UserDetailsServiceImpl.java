package com.unaagendamento.loginAuthentication.business;

import com.unaagendamento.loginAuthentication.entity.UserCredentials;
import com.unaagendamento.loginAuthentication.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Override
    @Transactional // Garante que a transação com o banco abra e feche corretamente
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        // 1. Busca o usuário no banco pelo email
        UserCredentials user = userCredentialsRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // 2. Converte a Entity (do banco) para UserDetails (do Spring Security)
        return UserDetailsImpl.build(user);
    }
}
