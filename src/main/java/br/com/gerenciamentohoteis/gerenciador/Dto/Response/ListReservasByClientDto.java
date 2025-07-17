package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListReservasByClientDto {
    private String nomeCliente;
    private Integer numero;
    private Double precoNoite;
    private String nomeHotel;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
}
