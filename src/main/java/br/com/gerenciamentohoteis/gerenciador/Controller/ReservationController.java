package br.com.gerenciamentohoteis.gerenciador.Controller;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateReservationDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListReservationsByClientDTO;
import br.com.gerenciamentohoteis.gerenciador.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/confirmar")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservar")
    public ResponseEntity<CreateReservationDTO> reservingRoom(@RequestParam String nomeHotel , @RequestParam Integer numero, @RequestBody CreateReservationDTO reservaDto){
        reservationService.booking(nomeHotel,numero,reservaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{userId}/reservas")
    public ResponseEntity<Stream<ListReservationsByClientDTO>> checkingReservations(@PathVariable ("userId") Long userId){
     Stream<ListReservationsByClientDTO>clientDtoStream = reservationService.checkingReservation(userId);
     return ResponseEntity.status(HttpStatus.OK).body(clientDtoStream);
    }
}
