package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatedHotelDto {
    private String nome;
    private String endereco;
    private LocalDate criacaoHotel;

}
