package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String email;
    private String password;
}
