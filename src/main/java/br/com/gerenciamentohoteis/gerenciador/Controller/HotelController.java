package br.com.gerenciamentohoteis.gerenciador.Controller;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.*;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListHotelDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListQuartoDto;
import br.com.gerenciamentohoteis.gerenciador.Service.HotelService;
import br.com.gerenciamentohoteis.gerenciador.Service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity<CreatedHotelDto> criandoHotel(@RequestBody CreatedHotelDto dto){
        hotelService.createdHotel(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<Stream<ListHotelDto>> listarHoteis(){
        Stream<ListHotelDto> dtoStream = hotelService.listaDeHoteis();
        return ResponseEntity.ok().body(dtoStream);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetById> getHotelById (@PathVariable ("id") Long id){
        GetById getById = hotelService.getHotelById(id);
        return ResponseEntity.ok().body(getById);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHotelById(@PathVariable ("id") Long id, @RequestBody UpdateHotelDto updateHotelDto){
        hotelService.updateById(id,updateHotelDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        hotelService.deleteHotelById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{hotelId}/quarto")
    public ResponseEntity<CreatedQuartoDto> criarUmQuarto(@PathVariable ("hotelId") Long hotelId, @RequestBody CreatedQuartoDto createdQuartoDto){
        quartoService.createdQuarto(hotelId,createdQuartoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{hotelId}/quarto")
    public ResponseEntity<Stream<ListQuartoDto>> listarQuartos(@PathVariable ("hotelId") Long hotelId){
        Stream<ListQuartoDto> quartoDtoStream = quartoService.listQuartos(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(quartoDtoStream);
    }
    @GetMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<GetQuartoByHotel> quartoByHotel(@PathVariable("hotelId") Long hotelId, @PathVariable("id") Long id){
        GetQuartoByHotel getQuarto = quartoService.quartoByHotel(hotelId,id);
        return ResponseEntity.status(HttpStatus.OK).body(getQuarto);
    }
    @DeleteMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<Void> deleteQuarto(@PathVariable("hotelId")Long hotelId, @PathVariable("id")Long id){
        quartoService.deleteQuartoById(hotelId,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<Void> updateQuartoById(@PathVariable("hotelId")Long hotelId, @PathVariable("id")Long id,@RequestBody UpdateQuartoDto dto){
        quartoService.updateQuarto(hotelId,id,dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

