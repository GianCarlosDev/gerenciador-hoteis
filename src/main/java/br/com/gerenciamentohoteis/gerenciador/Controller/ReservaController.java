package br.com.gerenciamentohoteis.gerenciador.Controller;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateReservaDto;
import br.com.gerenciamentohoteis.gerenciador.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quarto")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping("/reservar")
    public ResponseEntity<CreateReservaDto> reservandoQuarto(@RequestParam String nomeHotel ,@RequestParam Integer numero, @RequestBody CreateReservaDto reservaDto){
        reservaService.reservando(nomeHotel,numero,reservaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
