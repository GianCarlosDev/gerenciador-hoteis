package br.com.gerenciamentohoteis.gerenciador.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListReservationsByClientDTO {
    private Integer number;
    private Double nightPrice;
    private String nameHotel;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
}
