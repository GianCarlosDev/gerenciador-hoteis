package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.UserTypes;

public record MyInformationDTO(Long id, String nameUser, UserTypes role) {
}
