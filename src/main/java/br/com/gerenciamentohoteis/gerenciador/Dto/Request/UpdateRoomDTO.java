package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.RoomTypes;

public record UpdateRoomDTO(RoomTypes rooms, Double nightPrice, Integer numero) {
}
