package com.unaagendamento.loginAuthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unaagendamento.loginAuthentication.entity.UserCredentials;


@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String>{

    Optional<UserCredentials> findByEmail(String email);
} 
