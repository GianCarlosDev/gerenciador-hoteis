package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.TiposQuartos;

public record ListQuartoDto(TiposQuartos quartos,Integer numero, Double precoNoite,Boolean disponivel) {
}
