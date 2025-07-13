package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.TiposQuartos;
import lombok.Data;

@Data
public class CreatedQuartoDto {
    private TiposQuartos quartos;
    private Double precoNoite;
    private Integer numero;
}
