package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.TiposQuartos;

public record UpdateQuartoDto(TiposQuartos quartos,Double precoNoite, Integer numero) {
}
