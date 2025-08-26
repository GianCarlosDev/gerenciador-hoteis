package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.RoomTypes;
import lombok.Data;

@Data
public class CreatedRoomDTO {
    private RoomTypes rooms;
    private Double nightPrice;
    private Integer number;
}
