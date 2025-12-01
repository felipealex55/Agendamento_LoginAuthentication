package com.unaagendamento.loginAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSyncDTO {

    // Estes são os dados que o Serviço de Cadastro vai nos enviar
    private String email;
    private String senha;
    
    // Se o cadastro tiver nome ou telefone e salvamos aqui futuramente,
    // adicione os campos aqui e na Entity UserCredentials.
}