package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatedHotelDto {
    private String nomeHotel;
    private String endereco;
    private LocalDate criacaoHotel;

}
