package br.com.gerenciamentohoteis.gerenciador.Controller;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.*;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListHotelDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListRoomDTO;
import br.com.gerenciamentohoteis.gerenciador.Service.HotelService;
import br.com.gerenciamentohoteis.gerenciador.Service.RoomService;
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
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<CreatedHotelDTO> createHotel(@RequestBody CreatedHotelDTO dto){
        hotelService.createdHotel(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<Stream<ListHotelDTO>> listHotel(){
        Stream<ListHotelDTO> dtoStream = hotelService.listHotel();
        return ResponseEntity.ok().body(dtoStream);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetHotelByIdDTO> getHotelById (@PathVariable ("id") Long id){
        GetHotelByIdDTO getHotelByIdDTO = hotelService.getHotelById(id);
        return ResponseEntity.ok().body(getHotelByIdDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHotelById(@PathVariable ("id") Long id, @RequestBody UpdateHotelDTO updateHotelDto){
        hotelService.updateById(id,updateHotelDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable("id") Long id){
        hotelService.deleteHotelById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{hotelId}/quarto")
    public ResponseEntity<CreatedRoomDTO> createRoom(@PathVariable ("hotelId") Long hotelId, @RequestBody CreatedRoomDTO createdRoomDto){
        roomService.CreateRoom(hotelId, createdRoomDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{hotelId}/quarto")
    public ResponseEntity<Stream<ListRoomDTO>> listRoom(@PathVariable ("hotelId") Long hotelId){
        Stream<ListRoomDTO> quartoDtoStream = roomService.listRoom(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(quartoDtoStream);
    }
    @GetMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<GetRoomByHotelDTO> getRoomById(@PathVariable("hotelId") Long hotelId, @PathVariable("id") Long id){
        GetRoomByHotelDTO getQuarto = roomService.roomByHotel(hotelId,id);
        return ResponseEntity.status(HttpStatus.OK).body(getQuarto);
    }
    @DeleteMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<Void> deleteRoom(@PathVariable("hotelId")Long hotelId, @PathVariable("id")Long id){
        roomService.deleteRoomById(hotelId,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{hotelId}/{id}/quarto")
    public ResponseEntity<Void> updateRoomById(@PathVariable("hotelId")Long hotelId, @PathVariable("id")Long id, @RequestBody UpdateRoomDTO dto){
        roomService.updateRoom(hotelId,id,dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

