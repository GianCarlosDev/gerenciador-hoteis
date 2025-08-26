package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.UserTypes;
import lombok.Data;

@Data
public class CreateUserDTO {
    private String nameUser;
    private String email;
    private String password;
    private UserTypes role;

}
