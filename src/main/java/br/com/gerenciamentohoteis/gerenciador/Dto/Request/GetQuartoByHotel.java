package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.TiposQuartos;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetQuartoByHotel {
    private String nome;
    private TiposQuartos quartos;
    private Double precoNoite;
    private Boolean disponivel;
    private Integer numero;

}
