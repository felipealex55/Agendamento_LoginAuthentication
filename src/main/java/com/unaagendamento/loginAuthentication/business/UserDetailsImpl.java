package com.unaagendamento.loginAuthentication.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unaagendamento.loginAuthentication.entity.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    // Construtor
    public UserDetailsImpl(String id, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // MÉTODO ESTÁTICO BUILD (O Tradutor)
    // Ele recebe a Entity UserCredentials e cria um UserDetailsImpl
    public static UserDetailsImpl build(UserCredentials user) {

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getSenha(),
                Collections.emptyList());
    }

    // Métodos Spring Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Retorna a lista de permissões
    }

    @Override
    public String getPassword() {
        return password; // Retorna a senha do banco
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Controles de Conta

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }
}
