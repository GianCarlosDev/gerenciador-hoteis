package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.RoomTypes;

public record ListRoomDTO(RoomTypes quartos, Integer numero, Double precoNoite, Boolean disponivel) {
}
