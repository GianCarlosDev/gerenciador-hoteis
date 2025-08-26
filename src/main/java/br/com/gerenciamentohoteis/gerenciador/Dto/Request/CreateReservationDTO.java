package br.com.gerenciamentohoteis.gerenciador.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReservationDTO {
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
    private LocalDateTime checkin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
    private LocalDateTime checkout;
}
