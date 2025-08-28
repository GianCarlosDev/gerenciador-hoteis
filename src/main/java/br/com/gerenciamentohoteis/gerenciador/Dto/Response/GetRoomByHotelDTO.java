package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.RoomTypes;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetRoomByHotelDTO {
    private String nome;
    private RoomTypes quartos;
    private Double precoNoite;
    private Boolean disponivel;
    private Integer numero;

}
