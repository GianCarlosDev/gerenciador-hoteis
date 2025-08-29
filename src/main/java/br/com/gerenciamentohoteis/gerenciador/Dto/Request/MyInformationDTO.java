package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.UserTypes;

public record MyInformationDTO(Long userId, String nameUser, UserTypes role) {
}
